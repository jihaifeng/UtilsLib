package com.jihf.utilslib.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.jihf.androidutils.swipback.SwipeBackHelper;
import com.jihf.androidutils.tools.ActivityUtils;

/**
 * Func：管理Activity的base类
 * User：jihf
 * Data：2016-12-15
 * Time: 15:39
 * Mail：jihaifeng@raiyi.com
 */

public class BaseActivity extends AppCompatActivity {
  public static String TAG = BaseActivity.class.getSimpleName().trim();
  private Context mBaseContext;
  private Context mCurrentContext;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SwipeBackHelper.onCreate(this);
    SwipeBackHelper.getCurrentPage(this)
        .setSwipeBackEnable(true)
        .setSwipeSensitivity(0.5f)
        .setSwipeRelateEnable(true)
        .setSwipeRelateOffset(300);
    mBaseContext = this;
    setActivityStatus(this);
  }

  private void setActivityStatus(Activity activity) {
    //设置当前栈顶Activity
    mCurrentContext = activity;
    //设置Activity强制竖屏显示
    activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    // 向Acitivty栈（数据类型为List）中添加Activity
    ActivityUtils.getInstance().addActivity(activity);
    //设置TAG
    TAG = activity.getClass().getSimpleName().trim();
  }

  @Override public void finish() {
    super.finish();
    Log.i(TAG, "-------finish------");
    ActivityUtils.getInstance().removeActivity(this);
  }

  public static boolean canGoBack() {
    return ActivityUtils.getInstance().getActivityNum() > 1;
  }

  public Context getCurrentActivity() {
    if (null == mCurrentContext) {
      mCurrentContext = this;
    }
    return mCurrentContext;
  }

  @Override protected void onStart() {
    super.onStart();
    Log.i(TAG, "-------onStart------");
  }

  @Override protected void onPause() {
    super.onPause();
    Log.i(TAG, "-------onPause------");
  }

  @Override protected void onResume() {
    super.onResume();
    Log.i(TAG, "-------onResume------");
  }

  @Override protected void onStop() {
    super.onStop();
    Log.i(TAG, "-------onStop------");
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    SwipeBackHelper.onDestroy(this);
    // 在Activity栈中移除Activity
    ActivityUtils.getInstance().removeActivity(this);
    Log.i(TAG, "-------onDestroy------");
  }

  @Override protected void onRestart() {
    super.onRestart();
    Log.i(TAG, "-------onRestart------");
  }

  @Override protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    SwipeBackHelper.onPostCreate(this);
  }

  public void startActivity(Context from, Class to) {
    Intent intent = new Intent();
    intent.setClass(from, to);
    startActivity(intent);
  }

  public void startActivityWithBundle(Context from, Class to, Bundle bundle) {
    Intent intent = new Intent();
    intent.putExtras(bundle);
    intent.setClass(from, to);
    startActivity(intent);
  }
}
