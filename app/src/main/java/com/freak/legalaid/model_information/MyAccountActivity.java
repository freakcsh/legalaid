package com.freak.legalaid.model_information;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.library.base.SimpleActivity;

public class MyAccountActivity extends SimpleActivity implements View.OnClickListener {

    ImageView mBack;
    TextView mTitletext;
    ImageView mRightBtn;
    TextView mRightText;
    RelativeLayout mRlTitlebar;
    TextView mTvRental;
    TextView mTvAccountMoney;
    TextView mTvBalance;
    TextView mTvIncome;
    LinearLayout mTopbar;
    RelativeLayout mLlBill;
    RelativeLayout mLlPayPassword;
    RelativeLayout mLlAccountBinding;
    Button mButton;
    private Intent intent;

    //    private ToastDialog dialog;
    @Override
    protected int getLayout() {
        return R.layout.my_account;
    }

    @Override
    protected void initEventAndData() {
        initView();
    }

    private void initView() {
        mBack = findViewById(R.id.back);
        mTitletext = findViewById(R.id.titletext);
        mRightBtn = findViewById(R.id.right_btn);
        mRightText = findViewById(R.id.right_text);
        mRlTitlebar = findViewById(R.id.rl_titlebar);
        mTvRental = findViewById(R.id.tv_rental);
        mTvAccountMoney = findViewById(R.id.tv_account_money);
        mTvBalance = findViewById(R.id.tv_balance);
        mTvIncome = findViewById(R.id.tv_income);
        mTopbar = findViewById(R.id.topbar);
        mLlBill = findViewById(R.id.ll_bill);
        mLlPayPassword = findViewById(R.id.ll_pay_password);
        mLlAccountBinding = findViewById(R.id.ll_account_binding);
        mButton = findViewById(R.id.button);

        mBack.setOnClickListener(this);
        mLlBill.setOnClickListener(this);
        mLlPayPassword.setOnClickListener(this);
        mLlAccountBinding.setOnClickListener(this);
        mButton.setOnClickListener(this);

        mBack.setImageResource(R.mipmap.return_bt);
        mTitletext.setText(R.string.mine_account);
        mTitletext.setTextColor(getResources().getColor(R.color.text_back));
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
//                intent=new Intent(getApplicationContext(),MainActivity.class);//若需跳转到Minefragment可传递值让mainActivity启动
//                intent.putExtra("grxx",4);
                finish();
//                startActivity(intent);
                break;
            case R.id.ll_bill:
                intent = new Intent();
                intent.setClass(getApplicationContext(),BillActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_pay_password:
                intent = new Intent(getApplicationContext(), PayPassWordActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_account_binding:
//                intent = new Intent(getApplicationContext(), AccountBindingActivity.class);
//                startActivity(intent);
                break;
            case R.id.button:
//                intent = new Intent(this, MyAccountRechargeActivity.class);
//                startActivity(intent);
                //  Toast.makeText(this, " 充值被点击了", Toast.LENGTH_LONG).show();
                Intent intent=new Intent();
                intent.setClass(this,MyAccountRechargeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
