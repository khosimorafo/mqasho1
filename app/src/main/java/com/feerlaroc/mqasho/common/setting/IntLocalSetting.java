package com.feerlaroc.mqasho.common.setting;

import android.content.SharedPreferences;

/**
 * Local setting for any values of type {@link Integer}.
 */
public class IntLocalSetting extends AbstractLocalSetting<Integer> {


  public IntLocalSetting(SharedPreferences preferences, String key) {
    super(preferences, key);
  }

  @Override
  protected Integer doGet() {
    return preferences.getInt(key, 0);
  }

  @Override
  public void set(Integer value) {
    apply(preferences.edit().putInt(key, value));
  }
}
