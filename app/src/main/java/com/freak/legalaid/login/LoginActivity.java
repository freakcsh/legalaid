package com.freak.legalaid.login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.bean.LoginCommonUserBean;
import com.freak.legalaid.bean.LoginLawyerUserBean;
import com.freak.legalaid.library.base.BaseActivity;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.model_home.MainActivity;
import com.freak.legalaid.utils.ToastUtil;
import com.pkmmte.view.CircularImageView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imgReturn;
    private TextView tvName, loginBtn, loginForgetPassword, loginRegister;
    private CircularImageView mCircularImageView;
    private CheckBox cbUserLawyer, cbUserCommon;
    private EditText loginPhone, loginPassword;
    private ImageView loginPasswordShow;
    private LinearLayout loginWeChat;
    private boolean passwordShop = false;
    private RealmHelper mRealmHelper;
    private String type;
    private boolean aBoolean;
    private List<LoginCommonUserBean> loginCommonUserBeans;
    private List<LoginLawyerUserBean> loginLawyerUserBeans;
    private String commonUserName;
    private String commonPassword;


    @Override
    protected int getLayout() {
        return R.layout.login;
    }

    @Override
    protected void initEventAndData() {
        imgReturn = findViewById(R.id.title_return);
        tvName = findViewById(R.id.title_name);
        mCircularImageView = findViewById(R.id.login_img);
        cbUserLawyer = findViewById(R.id.cb_user_lawyer);
        cbUserCommon = findViewById(R.id.cb_user_common);
        loginPhone = findViewById(R.id.login_phone);
        loginPassword = findViewById(R.id.login_password);
        loginPasswordShow = findViewById(R.id.login_password_show);
        loginBtn = findViewById(R.id.login_btn);
        loginForgetPassword = findViewById(R.id.login_forget_password);
        loginRegister = findViewById(R.id.login_register);
        loginWeChat = findViewById(R.id.login_we_chat);

        tvName.setText("登录");
        imgReturn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        loginRegister.setOnClickListener(this);
        loginForgetPassword.setOnClickListener(this);
        loginPasswordShow.setOnClickListener(this);
        mRealmHelper = new RealmHelper();
        cbUserCommon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cbUserLawyer.setChecked(false);
                }else {
                    cbUserLawyer.setChecked(true);
                }
            }
        });

        cbUserLawyer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    cbUserCommon.setChecked(false);
                }else {
                    cbUserCommon.setChecked(true);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录
            case R.id.login_btn:
                login();
                break;
            //注册
            case R.id.login_register:
                register();
                break;
            //忘记密码
            case R.id.login_forget_password:
                forgetPassword();
                break;
            //显示密码
            case R.id.login_password_show:
                if (!passwordShop) {
                    //显示密码
                    loginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordShop = true;
                } else {
                    //隐藏密码
                    loginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordShop = false;
                }
                break;
            case R.id.title_return:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        Intent registerIntent = new Intent();
        registerIntent.setClass(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);

    }

    /**
     * 忘记密码
     */
    private void forgetPassword() {
        Intent forgetIntent = new Intent();
        forgetIntent.setClass(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(forgetIntent);

    }

    /**
     * 登陆
     */
    private void login() {
        if (TextUtils.isEmpty(loginPhone.getText().toString())) {
            ToastUtil.shortShow("请输入用户名");
        } else if (TextUtils.isEmpty(loginPassword.getText().toString())) {
            ToastUtil.shortShow("请输入密码");
        } else {
            if (cbUserCommon.isChecked()) {
                type = "common";
            }
            if (cbUserLawyer.isChecked()) {
                type = "lawyer";
            }
            Log.e("freak","type:\n"+type);
            if ("common".equals(type)){
                aBoolean = mRealmHelper.selectCommonUser(loginPhone.getText().toString());
            }else if ("lawyer".equals(type)){
                aBoolean = mRealmHelper.selectLawyerUser(loginPhone.getText().toString());
            }

            if (aBoolean) {
                if ("common".equals(type)){
                    loginCommonUserBeans = DataSupport.where("type = ?", type).find(LoginCommonUserBean.class);
                   for (int i=0;i<loginCommonUserBeans.size();i++){
                       commonUserName = loginCommonUserBeans.get(i).getUserName();
                       commonPassword = loginCommonUserBeans.get(i).getPassword();
                   }
                   if (commonUserName.equals(loginPhone.getText().toString().trim())){
                       if (commonPassword.equals(loginPassword.getText().toString().trim())){
                           Intent loginIntent = new Intent();
                           loginIntent.setClass(LoginActivity.this, MainActivity.class);
                           startActivity(loginIntent);
                           ToastUtil.shortShow("登录成功");
                           finish();
                       }else {
                           ToastUtil.shortShow("密码不正确，请重新输入。");
                       }
                   }else {
                       ToastUtil.shortShow("用户名不正确，请重新输入。");
                   }
                }else if ("lawyer".equals(type)){
                    loginLawyerUserBeans = DataSupport.where("type = ?", type).find(LoginLawyerUserBean.class);
                    for (int i=0;i<loginLawyerUserBeans.size();i++){
                        commonUserName = loginLawyerUserBeans.get(i).getUserName();
                        commonPassword = loginLawyerUserBeans.get(i).getPassword();
                    }
                    if (commonUserName.equals(loginPhone.getText().toString().trim())){
                        if (commonPassword.equals(loginPassword.getText().toString().trim())){
                            Intent loginIntent = new Intent();
                            loginIntent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(loginIntent);
                            ToastUtil.shortShow("登录成功");
                            finish();
                        }else {
                            ToastUtil.shortShow("密码不正确，请重新输入。");
                        }
                    }else {
                        ToastUtil.shortShow("用户名不正确，请重新输入。");
                    }
                }

            }else {
                ToastUtil.shortShow("该用户未注册，请注册之后再登录！");
            }
        }
    }
}
