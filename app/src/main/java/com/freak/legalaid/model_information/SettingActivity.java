package com.freak.legalaid.model_information;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.freak.legalaid.R;

public class SettingActivity extends AppCompatActivity {
    private TextView title_return,title_name;
    private TextView tv_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tv_about = (TextView) findViewById(R.id.tv_about);
        title_name= (TextView) findViewById(R.id.title_name);
        title_return= (TextView) findViewById(R.id.title_return);
        title_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_name.setText("设置");
        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }
}
