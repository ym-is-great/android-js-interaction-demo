package com.caiyiming.js_comm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  protected WebView mWebView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mWebView = (WebView) findViewById(R.id.webview);

    // 启用 JavaScript
    WebSettings webSettings = mWebView.getSettings();
    webSettings.setJavaScriptEnabled(true);

    // 向 JavaScript 注入 Java 对象（以便暴露函数给网页调用）
    mWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

    // 加载本地网页文件
    mWebView.loadUrl("file:///android_asset/index.html");

    // Say Hello to Web 按钮点击事件
    Button button1 = (Button) findViewById(R.id.button1);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mWebView.loadUrl("javascript:showMessage('Hello, Web!')");
      }
    });

    // Get Return Value 按钮点击事件
    Button button2 = (Button) findViewById(R.id.button2);
    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mWebView.evaluateJavascript("javascript:showMessage('Hello, Web!')", new ValueCallback<String>() {
          @Override
          public void onReceiveValue(String value) {
            Toast.makeText(MainActivity.this, "value: " + value, Toast.LENGTH_SHORT).show();
          }
        });
      }
    });

  }
}
