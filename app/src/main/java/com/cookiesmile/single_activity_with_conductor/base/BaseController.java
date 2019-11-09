package com.cookiesmile.single_activity_with_conductor.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseController extends Controller {

  Unbinder unbinder;

  @LayoutRes
  protected abstract int layoutRes();

  @NonNull
  @Override
  protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
    View view = inflater.inflate(layoutRes(), container, false);
    unbinder = ButterKnife.bind(this, view);
    onViewBound(view);
    return view;
  }

  @Override
  protected void onDestroyView(@NonNull View view) {
    if (unbinder != null) {
      unbinder.unbind();
      unbinder = null;
    }
  }

  protected void onViewBound(View view) {
  }
}
