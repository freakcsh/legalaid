package com.freak.legalaid.model_information;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.library.base.BaseActivity;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAccountRechargeActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletext)
    TextView titletext;
    @BindView(R.id.right_btn)
    ImageView rightBtn;
    @BindView(R.id.right_text)
    TextView rightText;
    @BindView(R.id.rl_titlebar)
    RelativeLayout rlTitlebar;
    @BindView(R.id.recharge_amount_tv)
    TextView rechargeAmountTv;
    @BindView(R.id.recharge_money1)
    TextView rechargeMoney1;
    @BindView(R.id.recharge_money2)
    TextView rechargeMoney2;
    @BindView(R.id.recharge_money3)
    TextView rechargeMoney3;
    @BindView(R.id.recharge_money4)
    EditText rechargeMoney4;
    @BindView(R.id.rl_recharge_money_more_et)
    EditText rlRechargeMoneyMoreEt;
    @BindView(R.id.ll_recharge_moneys)
    LinearLayout llRechargeMoneys;
    @BindView(R.id.ll_recharge)
    LinearLayout llRecharge;
    @BindView(R.id.recharge_count_tv1)
    TextView rechargeCountTv1;
    @BindView(R.id.vie)
    View vie;
    @BindView(R.id.rl_recharge_count_img)
    ImageView rlRechargeCountImg;
    @BindView(R.id.rl_recharge_count_tv1)
    TextView rlRechargeCountTv1;
    @BindView(R.id.rl_recharge_count_tv2)
    TextView rlRechargeCountTv2;
    @BindView(R.id.img_alipay)
    ImageView imgAlipay;
    @BindView(R.id.rl_recharge_count)
    RelativeLayout rlRechargeCount;
    @BindView(R.id.ve)
    View ve;
    @BindView(R.id.rl_recharge_count2_img)
    ImageView rlRechargeCount2Img;
    @BindView(R.id.rl_recharge_count2_ll_tv1)
    TextView rlRechargeCount2LlTv1;
    @BindView(R.id.rl_recharge_count2_ll)
    AutoLinearLayout rlRechargeCount2Ll;
    @BindView(R.id.img_weixin)
    ImageView imgWeixin;
    @BindView(R.id.rl_recharge_count2)
    RelativeLayout rlRechargeCount2;
    @BindView(R.id.v3)
    View v3;
    @BindView(R.id.ll_recharge_count)
    AutoLinearLayout llRechargeCount;
    @BindView(R.id.bt_sure_recharge)
    Button btSureRecharge;


    private int mNeedMoney;

    private int mCurMoney;// 所选的钱


    private void initData() {
        back.setImageResource(R.mipmap.back);
        titletext.setTextColor(getResources().getColor(R.color.text_back));
        titletext.setText("充值");
    }


    @Override
    protected int getLayout() {
        return R.layout.pay;
    }

    @Override
    protected void initEventAndData() {
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.bt_sure_recharge, R.id.recharge_money1, R.id.recharge_money2, R.id.recharge_money3, R.id.recharge_money4})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
//                intent = new Intent(getApplicationContext(), MyAccountActivity.class);//若需跳转到Minefragment可传递值让mainActivity启动
//                startActivity(intent);
                finish();
                break;
            case R.id.bt_sure_recharge:
                //   recharge(1, MyApplication.getUserInfo().user_token);
               /* intent = new Intent(getApplicationContext(), MyAccountRechargeSuccessActivity.class);//
                finish();
                startActivity(intent);*/
//                recharge(0.01, MyApplication.getUserInfo().getTokenid());
//
//
//
//                if (mCurMoney == -1) {
//                    // 选择其他充值
//                    if (TextUtils.isEmpty(tvOtherMoney.getText().toString().trim())) {
//                        showToast("金额不能为空");
//                    } else {
//                        try {
//                            int money = Integer.parseInt(tvOtherMoney.getText().toString().trim());
//                            // TODO: 2017/9/9 用money充钱
////                            recharge(money, MyApplication.getUserInfo().getTokenid());
//                        } catch (Exception e) {
//                            showToast("输入的金额格式错误，请重新输入");
//                        }
//                    }
//                } else {
//                    // 面额充值5 - 10 - 50
//                    // TODO: 2017/9/9  用 mCurMoney充钱
////                    recharge(mCurMoney, MyApplication.getUserInfo().getTokenid());
//                }
                break;
            case R.id.recharge_money1:
                clickMoneyText(1);
                break;
            case R.id.recharge_money2:
                clickMoneyText(2);
                break;
            case R.id.recharge_money3:
                clickMoneyText(3);
                break;
            case R.id.recharge_money4:
                clickMoneyText(4);
                break;
        }
    }

    private void clickMoneyText(int type) {
        rechargeMoney1.setSelected(type == 1);
        rechargeMoney2.setSelected(type == 2);
        rechargeMoney3.setSelected(type == 3);
        mCurMoney = type == 1 ? 5 : type == 2 ? 10 : type == 3 ? 50 : -1;

        if (type != 4) {
            rechargeMoney4.setText("");
        }
    }

    @Override
    public void showError(String msg) {

    }
}
