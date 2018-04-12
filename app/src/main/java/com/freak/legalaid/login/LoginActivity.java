package com.freak.legalaid.login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freak.legalaid.R;
import com.freak.legalaid.app.Constants;
import com.freak.legalaid.bean.ImageBean;
import com.freak.legalaid.bean.LoginCommonUserBean;
import com.freak.legalaid.bean.LoginLawyerUserBean;
import com.freak.legalaid.imageloader.GlideImageLoader;
import com.freak.legalaid.library.base.BaseActivity;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.model_home.MainActivity;
import com.freak.legalaid.utils.SPUtils;
import com.freak.legalaid.utils.ToastUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvReturn;
    private TextView tvName, loginBtn, loginForgetPassword, loginRegister;
    private ImageView mCircularImageView;
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

    private ImagePicker imagePicker;
    private ArrayList<ImageItem> images = null;
    private boolean selectImage;


    @Override
    protected int getLayout() {
        return R.layout.login;
    }

    @Override
    protected void initEventAndData() {
        tvReturn = findViewById(R.id.title_return);
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
        tvReturn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        loginRegister.setOnClickListener(this);
        loginForgetPassword.setOnClickListener(this);
        loginPasswordShow.setOnClickListener(this);
        mCircularImageView.setOnClickListener(this);
        mRealmHelper = new RealmHelper();
        cbUserCommon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbUserLawyer.setChecked(false);
                } else {
                    cbUserLawyer.setChecked(true);
                }
            }
        });

        cbUserLawyer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbUserCommon.setChecked(false);
                } else {
                    cbUserCommon.setChecked(true);
                }
            }
        });

        initImagePicker();
        setImage();


    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    private void setImage() {
        selectImage = mRealmHelper.selectImage();
        if (selectImage){
            ImageBean first = DataSupport.where("id = ?", "1").findFirst(ImageBean.class);
            String path=first.getPath();
            imagePicker.getImageLoader().displayImage(LoginActivity.this, path, mCircularImageView, 140, 140);
        }
    }

    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        /**
         * 设置图片缓存方式
         */
        imagePicker.setImageLoader(new GlideImageLoader());

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
            case R.id.login_img:
                openImageLoader();
                break;
            default:
                break;
        }
    }

    /**
     * 打开图片选择
     */
    private void openImageLoader() {
        /**
         * 设置选择的模式、单张选择和多张选择
         * setMultiMode  为false是是单张选择  为true的时候是多张选择
         */
        imagePicker.setMultiMode(false);
        /**
         * 设置图片裁剪样式
         * CropImageView.Style.RECTANGLE  矩形
         * CropImageView.Style.CIRCLE 圆形
         */
        imagePicker.setStyle(CropImageView.Style.CIRCLE);
        /**
         * 设置圆形图片半径
         */
        Integer radius = Integer.valueOf("140");
        radius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, getResources().getDisplayMetrics());
        imagePicker.setFocusWidth(radius * 2);
        imagePicker.setFocusHeight(radius * 2);
        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
        //ImagePicker.getInstance().setSelectedImages(images);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                mRealmHelper.addImage(images,images.get(0).path);
                imagePicker.getImageLoader().displayImage(LoginActivity.this, images.get(0).path, mCircularImageView, 140, 140);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
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
            Log.e("freak", "type:\n" + type);
            if ("common".equals(type)) {
                aBoolean = mRealmHelper.selectCommonUser(loginPhone.getText().toString());
            } else if ("lawyer".equals(type)) {
                aBoolean = mRealmHelper.selectLawyerUser(loginPhone.getText().toString());
            }

            if (aBoolean) {
                if ("common".equals(type)) {
                    loginCommonUserBeans = DataSupport.where("userName = ?", loginPhone.getText().toString().trim()).find(LoginCommonUserBean.class);
                    for (int i = 0; i < loginCommonUserBeans.size(); i++) {
                        commonUserName = loginCommonUserBeans.get(i).getUserName();
                        commonPassword = loginCommonUserBeans.get(i).getPassword();
                    }
                    if (commonUserName.equals(loginPhone.getText().toString().trim())) {
                        if (commonPassword.equals(loginPassword.getText().toString().trim())) {
                            Intent loginIntent = new Intent();
                            loginIntent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(loginIntent);
                            ToastUtil.shortShow("登录成功");
                            SPUtils.put(this, Constants.TYPE,type);
                            String o = (String) SPUtils.get(this, Constants.USERNAME, "");
                            if (TextUtils.isEmpty(o)){
                                SPUtils.put(this,Constants.USERNAME,commonUserName);
                            }
                            SPUtils.put(this,Constants.REAL_M_HELPER_USERNAME,commonUserName);
                            finish();
                        } else {
                            ToastUtil.shortShow("密码不正确，请重新输入。");
                        }
                    } else {
                        ToastUtil.shortShow("用户名不正确，请重新输入。");
                    }
                } else if ("lawyer".equals(type)) {
                    loginLawyerUserBeans = DataSupport.where("userName = ?", loginPhone.getText().toString().trim()).find(LoginLawyerUserBean.class);
                    for (int i = 0; i < loginLawyerUserBeans.size(); i++) {
                        commonUserName = loginLawyerUserBeans.get(i).getUserName();
                        commonPassword = loginLawyerUserBeans.get(i).getPassword();
                    }
                    if (commonUserName.equals(loginPhone.getText().toString().trim())) {
                        if (commonPassword.equals(loginPassword.getText().toString().trim())) {
                            Intent loginIntent = new Intent();
                            loginIntent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(loginIntent);
                            ToastUtil.shortShow("登录成功");
                            finish();
                        } else {
                            ToastUtil.shortShow("密码不正确，请重新输入。");
                        }
                    } else {
                        ToastUtil.shortShow("用户名不正确，请重新输入。");
                    }
                }

            } else {
                ToastUtil.shortShow("该用户未注册，请注册之后再登录！");
            }
        }
    }
}
