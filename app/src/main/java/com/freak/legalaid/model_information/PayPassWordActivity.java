package com.freak.legalaid.model_information;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.library.base.BaseActivity;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.login.ForgetPasswordActivity;

public class PayPassWordActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mBack;
    private RelativeLayout mRlPayPassword;
    private RelativeLayout mRlForgotPassword;
    private RelativeLayout mRlSetPassword;
    private TextView mTitletext;
    private Intent intent;

    @Override
    protected int getLayout() {
        return R.layout.pay_password;
    }

    @Override
    protected void initEventAndData() {
        initView();
    }

    private void initView() {
        mBack = findViewById(R.id.back);
        mRlPayPassword=findViewById(R.id.rl_pay_password);
        mRlForgotPassword=findViewById(R.id.rl_forgot_password);
        mRlSetPassword=findViewById(R.id.rl_set_password);
        mTitletext=findViewById(R.id.titletext);

        mBack.setImageResource(R.mipmap.return_bt);
        mTitletext.setText("支付密码");
        mTitletext.setTextColor(getResources().getColor(R.color.text_back));
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
//                intent=new Intent(getApplicationContext(),MyAccountActivity.class);
                finish();
//                startActivity(intent);
                break;
            case R.id.rl_pay_password:
//                intent = new Intent(getApplicationContext(), ModifyPayPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_forgot_password:
                intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                intent.putExtra("type", "fogerpsw");
                startActivity(intent);
                break;
            case R.id.rl_set_password:
                intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                intent.putExtra("type", "setpsw");
                startActivity(intent);
            default:
                break;
        }
    }
}
