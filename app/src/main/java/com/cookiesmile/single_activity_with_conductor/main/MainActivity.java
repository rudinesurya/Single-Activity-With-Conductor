package com.cookiesmile.single_activity_with_conductor.main;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.cookiesmile.single_activity_with_conductor.R;

public class MainActivity extends AppCompatActivity {

  private Router router;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ViewGroup screenContainer = findViewById(R.id.screen_container);
    router = Conductor
        .attachRouter(this, screenContainer, savedInstanceState);
    if (!router.hasRootController()) {
      router.setRoot(RouterTransaction.with(new MainController()));
    }
  }

  @Override
  public void onBackPressed() {
    if (!router.handleBack()) {
      super.onBackPressed();
    }
  }
}
