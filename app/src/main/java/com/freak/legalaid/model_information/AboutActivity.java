package com.freak.legalaid.model_information;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.freak.legalaid.R;

public class AboutActivity extends AppCompatActivity {

    private TextView title_name;
    private TextView title_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        title_name= (TextView) findViewById(R.id.title_name);
        title_return= (TextView) findViewById(R.id.title_return);
        title_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_name.setText("设置");
    }
}
