package com.jihf.androidutils.tools;

import android.app.ProgressDialog;
import android.text.TextUtils;

/**
 * Func：
 * User：jihf
 * Data：2017-01-16
 * Time: 13:32
 * Mail：jihaifeng@raiyi.com
 */
public class ProgressDialogUtils {
  public static ProgressDialogUtils instance;
  private static ProgressDialog progressDialog;

  /**
   * 显示进度对话框
   * @param message 内容
   */
  public static void showProgressDialog(String message) {
    if (progressDialog == null) {
      progressDialog = new ProgressDialog(Utils.getContext());
      if (!TextUtils.isEmpty(message)) {
        progressDialog.setMessage(message);
      }
      progressDialog.setCanceledOnTouchOutside(false);
    }

    progressDialog.show();
  }

  /**
   * 关闭进度对话框
   */
  public static void hideProgressDialog() {
    if (progressDialog != null) {
      progressDialog.dismiss();
    }
  }
}
