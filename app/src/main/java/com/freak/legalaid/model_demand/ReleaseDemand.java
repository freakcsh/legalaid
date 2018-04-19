package com.freak.legalaid.model_demand;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.app.Constants;
import com.freak.legalaid.event.UserEvent;
import com.freak.legalaid.library.base.BaseActivity;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.library.rxjava.RxBus;
import com.freak.legalaid.utils.SPUtils;
import com.freak.legalaid.utils.ToastUtil;

public class ReleaseDemand extends BaseActivity {
    private EditText edt_release_title, edt_release_context, edt_release_reward, edt_release_address, edt_release_start_time, edt_release_end_time;
    private TextView tv_release_user_name;
    private Button btn_release;
    private RealmHelper mRealmHelper;
    private String userName;

    @Override
    protected int getLayout() {
        return R.layout.activity_release_demand;
    }

    @Override
    protected void initEventAndData() {
        initView();
    }

    private void initView() {
        tv_release_user_name = findViewById(R.id.tv_release_user_name);
        edt_release_title = findViewById(R.id.edt_release_title);
        edt_release_context = findViewById(R.id.edt_release_context);
        edt_release_reward = findViewById(R.id.edt_release_reward);
        edt_release_address = findViewById(R.id.edt_release_address);
        edt_release_start_time = findViewById(R.id.edt_release_start_time);
        edt_release_end_time = findViewById(R.id.edt_release_end_time);
        btn_release = findViewById(R.id.btn_release);
        mRealmHelper = new RealmHelper();
        userName = (String) SPUtils.get(this, Constants.REAL_M_HELPER_USERNAME, "");
        if (!TextUtils.isEmpty(userName)) {
            tv_release_user_name.setText(userName);
        }

        btn_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_release_title.getText().toString().trim())) {
                    ToastUtil.shortShow("标题不能为空，请输入标题。");
                } else if (TextUtils.isEmpty(edt_release_context.getText().toString().trim())) {
                    ToastUtil.shortShow("正文不能为空，请输入正文。");
                } else if (TextUtils.isEmpty(edt_release_reward.getText().toString().trim())) {
                    ToastUtil.shortShow("酬金不能为空，请输入酬金。");
                } else if (TextUtils.isEmpty(edt_release_address.getText().toString().trim())) {
                    ToastUtil.shortShow("地址不能为空，请输入地址。");
                } else if (TextUtils.isEmpty(edt_release_start_time.getText().toString().trim())) {
                    ToastUtil.shortShow("起始日期不能为空，请输入起始日期。");
                } else if (TextUtils.isEmpty(edt_release_end_time.getText().toString().trim())) {
                    ToastUtil.shortShow("结束日期不能为空，请输入结束日期。");
                } else {
                    mRealmHelper.saveDemand("已发布", userName, edt_release_title.getText().toString().trim(), edt_release_context.getText().toString().trim(), edt_release_reward.getText().toString().trim(),
                            edt_release_address.getText().toString().trim(), "已发布", null, edt_release_start_time.getText().toString().trim(),
                            edt_release_end_time.getText().toString().trim(), null, null, null);
                    mRealmHelper.saveOrder("待接单", userName, edt_release_title.getText().toString().trim(), edt_release_context.getText().toString().trim(), edt_release_reward.getText().toString().trim(),
                            edt_release_address.getText().toString().trim(), "待接单", null, edt_release_start_time.getText().toString().trim(),
                            edt_release_end_time.getText().toString().trim(), null, null, null);
                    if (mRealmHelper.selectDemandState(edt_release_title.getText().toString().trim())) {
                        ToastUtil.shortShow("发布成功");
                        RxBus.getDefault().post(new UserEvent(1, "已发布"));
                        finish();
                    } else {
                        ToastUtil.shortShow("发布失败");
                    }

                }
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void showError(String msg) {

    }
}
