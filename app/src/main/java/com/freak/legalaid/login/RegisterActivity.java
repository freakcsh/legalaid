package com.freak.legalaid.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.library.base.BaseActivity;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.utils.ToastUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText registerPhone, registerPassword, edtReRegisterPassword;
    private TextView registerBtn;
    private TextView tvReturn;
    private TextView titleName;
    private boolean aBoolean;
    private RealmHelper mRealmHelper;
    private CheckBox cbRegisterCommon, cbRegisterLawyer;
    private String type;
    private boolean selectCommonUser;
    private boolean selectLawyerUser;

    @Override
    protected int getLayout() {
        return R.layout.login_register;
    }

    @Override
    protected void initEventAndData() {
        registerPhone = findViewById(R.id.register_phone);
        registerPassword = findViewById(R.id.register_password);
        edtReRegisterPassword = findViewById(R.id.edt_re_register_password);
        registerBtn = findViewById(R.id.register_btn);
        tvReturn = findViewById(R.id.title_return);
        titleName = findViewById(R.id.title_name);
        cbRegisterLawyer = findViewById(R.id.cb_register_lawyer);
        cbRegisterCommon = findViewById(R.id.cb_register_common);

        titleName.setText("注册");
        tvReturn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        mRealmHelper = new RealmHelper();
        /***
         * 普通用户注册监听
         */
        cbRegisterCommon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbRegisterLawyer.setChecked(false);
                } else {
                    cbRegisterLawyer.setChecked(true);
                }
            }
        });
        /**
         * 律师用户注册监听
         */
        cbRegisterLawyer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbRegisterCommon.setChecked(false);
                } else {
                    cbRegisterCommon.setChecked(true);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                register();
                break;
            case R.id.title_return:
                finish();
                break;
        }
    }

    /***
     * 注册
     */
    private void register() {
        if (TextUtils.isEmpty(registerPhone.getText().toString().trim()) || registerPhone.getText().toString().trim().length() != 11) {
            ToastUtil.shortShow("电话号码不能为空或者位数不对");
        } else if (TextUtils.isEmpty(registerPassword.getText().toString().trim())) {
            ToastUtil.shortShow("请输入密码。");
        } else if (TextUtils.isEmpty(edtReRegisterPassword.getText().toString().trim())) {
            ToastUtil.shortShow("请输入确认密码。");
        } else {
            aBoolean = isPhoneNumber(registerPhone.getText().toString().trim());
            if (aBoolean) {
                if (registerPassword.getText().toString().trim().equals(edtReRegisterPassword.getText().toString().trim())) {
                    if (cbRegisterCommon.isChecked()) {
                        type = "common";
                    }
                    if (cbRegisterLawyer.isChecked()) {
                        type = "lawyer";
                    }
                    if ("common".equals(type)) {
                        selectCommonUser = mRealmHelper.selectCommonUser(registerPhone.getText().toString().trim());
                        if (selectCommonUser) {
                            ToastUtil.shortShow("用户已经注册过，请直接进行登录。");
                        } else {
                            mRealmHelper.addUserLogin(type, registerPhone.getText().toString().trim(), "", registerPassword.getText().toString().trim(), "", "", "", "");
                            ToastUtil.shortShow("注册成功");
                            finish();
                        }
                    } else if ("lawyer".equals(type)) {
                        selectLawyerUser = mRealmHelper.selectLawyerUser(registerPhone.getText().toString().trim());
                        if (selectLawyerUser) {
                            ToastUtil.shortShow("用户已经注册过，请直接进行登录。");
                        } else {
                            mRealmHelper.addUserLogin(type, registerPhone.getText().toString().trim(), "", registerPassword.getText().toString().trim(), "", "", "", "");
                            ToastUtil.shortShow("注册成功");
                            finish();
                        }
                    }

                } else {
                    ToastUtil.shortShow("两次密码不一致。");
                }
            } else {
                ToastUtil.shortShow("请输入正确的电话号码.");
            }
        }
    }


    /**
     * 判断传入的字符串是否是一个手机号码,还未支持所有手机，还需要加入更多支持的手机号码类型
     *
     * @param strPhone 电话号码
     * @return
     */
    public static boolean isPhoneNumber(String strPhone) {
        String str = "^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(strPhone);
        return m.find();
    }
}
