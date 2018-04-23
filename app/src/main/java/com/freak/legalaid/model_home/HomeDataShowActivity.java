package com.freak.legalaid.model_home;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.freak.legalaid.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/25.
 */

public class HomeDataShowActivity extends Activity {
    private WebView webView;
    private TextView tvTbTitle;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_data_show);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
        webView = findViewById(R.id.web_view);
        tvTbTitle = findViewById(R.id.tv_tb_title);
        toolbar = findViewById(R.id.toolbar);
        initAppBar();
        initWebView(url);
        initWebSettings();
        initWebViewClient();
        initWebChromeClient();
    }

    private void initWebChromeClient() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, final String title) {
                super.onReceivedTitle(view, title);
                if (webView != null) {
                    webView.post(new Runnable() {
                        @Override
                        public void run() {
                            tvTbTitle.setText(TextUtils.isEmpty(title) ? "加载中..." : title);
                        }
                    });
                }
            }
        });
    }

    private void initWebViewClient() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
    }

    private void initWebSettings() {
        WebSettings settings = webView.getSettings();
        //支持获取手势焦点
        webView.requestFocusFromTouch();
        //支持JS
        settings.setJavaScriptEnabled(true);
        //支持插件
        settings.setPluginState(WebSettings.PluginState.ON);
        //设置适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持缩放
        settings.setSupportZoom(true);
        //隐藏原生得缩放控件
        settings.setDisplayZoomControls(false);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.supportMultipleWindows();
        settings.setSupportMultipleWindows(true);
        //设置缓存模式
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(webView.getContext().getCacheDir().getAbsolutePath());

        //设置可访问文件
        settings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true);
        //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setNeedInitialFocus(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");
    }

    private void initWebView(String url) {
        webView.loadUrl(url);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initAppBar() {
        toolbar.setTitle("载入中...");
//        this.setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
