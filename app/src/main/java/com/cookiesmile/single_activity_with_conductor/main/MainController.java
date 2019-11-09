package com.cookiesmile.single_activity_with_conductor.main;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.cookiesmile.single_activity_with_conductor.R;
import com.cookiesmile.single_activity_with_conductor.base.BaseController;
import com.cookiesmile.single_activity_with_conductor.screen.CurrentWeatherController;
import com.cookiesmile.single_activity_with_conductor.screen.ForecastWeatherController;
import com.cookiesmile.single_activity_with_conductor.screen.SettingsController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

import butterknife.BindView;

public class MainController extends BaseController {

  @BindView(R.id.screen_container)
  ViewGroup childContainer;
  @BindView(R.id.bot_nav)
  BottomNavigationView botNav;

  private Router childRouter;
  private BottomNavigationView.OnNavigationItemSelectedListener botNavListener = new OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
      Controller selection;
      switch (menuItem.getItemId()) {
        case R.id.current_weather:
          selection = new CurrentWeatherController();
          break;
        case R.id.forecast_weather:
          selection = new ForecastWeatherController();
          break;
        case R.id.settings:
          selection = new SettingsController();
          break;
        default:
          return false;
      }

      childRouter.setRoot(RouterTransaction.with(selection));
      return true;
    }
  };

  @Override
  protected int layoutRes() {
    return R.layout.screen_main;
  }

  @Override
  protected void onViewBound(View view) {
    childRouter = getChildRouter(childContainer);
    botNav.setOnNavigationItemSelectedListener(botNavListener);

    if (!childRouter.hasRootController()) {
      childRouter.setRoot(RouterTransaction.with(new CurrentWeatherController()));
    }
  }
}
