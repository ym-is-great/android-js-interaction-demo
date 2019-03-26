package com.caiyiming.js_comm;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {

  Context mContext;

  // 初始化接口并设置上下文
  WebAppInterface(Context c) {
    mContext = c;
  }

  // 显示短消息的接口
  @JavascriptInterface
  public void showToast(String text) {
    Toast.makeText(mContext, "Web: " + text, Toast.LENGTH_SHORT).show();
  }

}
