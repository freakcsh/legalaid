package com.freak.legalaid.model_information;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.app.Constants;
import com.freak.legalaid.bean.ImageBean;
import com.freak.legalaid.bean.LoginCommonUserBean;
import com.freak.legalaid.bean.LoginLawyerUserBean;
import com.freak.legalaid.event.UserEvent;
import com.freak.legalaid.imageloader.GlideImageLoader;
import com.freak.legalaid.library.base.BaseActivity;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.library.rxjava.BasePresenter;
import com.freak.legalaid.library.rxjava.RxBus;
import com.freak.legalaid.utils.SPUtils;
import com.freak.legalaid.utils.ToastUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

public class AlterInformationActivity extends BaseActivity implements View.OnClickListener {
    private EditText edtUserName, edtRealName, edtAge, edtSex, edtAddress, detPhone;
    private ImageView imgImage;
    private ImagePicker imagePicker;
    private ArrayList<ImageItem> images = null;
    private RealmHelper mRealmHelper;
    private String type;
    private String userName;

    @Override
    protected int getLayout() {
        return R.layout.my_alter_personal_information;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initEventAndData() {
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 在退出activity之后要解绑RxBus的观察者
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 初始化数据
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initData() {
        setImage();
        edtUserName.setInputType(InputType.TYPE_NULL);
        edtUserName.setBackground(null);
        edtRealName.setInputType(InputType.TYPE_NULL);
        edtRealName.setBackground(null);
        edtAge.setInputType(InputType.TYPE_NULL);
        edtAge.setBackground(null);
        edtSex.setInputType(InputType.TYPE_NULL);
        edtSex.setBackground(null);
        edtAddress.setInputType(InputType.TYPE_NULL);
        edtAddress.setBackground(null);
        detPhone.setInputType(InputType.TYPE_NULL);
        detPhone.setBackground(null);
        type = (String) SPUtils.get(this, Constants.TYPE, "");
        userName = (String) SPUtils.get(this, Constants.USERNAME, "");
        if (!TextUtils.isEmpty(type) && !TextUtils.isEmpty(userName)) {
            if ("common".equals(type)) {
                /**
                 * 普通用户表
                 */
                LoginCommonUserBean loginCommonUserBean = DataSupport.where("userName = ?", userName).findFirst(LoginCommonUserBean.class);
                /**
                 * 设置用户名
                 */
                if (!TextUtils.isEmpty(userName)) {
                    edtUserName.setText(userName);
                } else {
                    edtUserName.setText("无");
                }
                /**
                 * 设置真实姓名
                 */
                if (!TextUtils.isEmpty(loginCommonUserBean.getRealName())) {
                    edtRealName.setText(loginCommonUserBean.getRealName());
                } else {
                    edtRealName.setText("无");
                }
                /**
                 * 设置性别
                 */
                if (!TextUtils.isEmpty(loginCommonUserBean.getSex())) {
                    edtSex.setText(loginCommonUserBean.getSex());
                } else {
                    edtSex.setText("无");
                }
                /**
                 * 设置年龄
                 */
                if (!TextUtils.isEmpty(loginCommonUserBean.getAge())) {
                    edtAge.setText(loginCommonUserBean.getAge());
                } else {
                    edtAge.setText("无");
                }
                /**
                 * 设置地址
                 */
                if (!TextUtils.isEmpty(loginCommonUserBean.getAddress())) {
                    edtAddress.setText(loginCommonUserBean.getAddress());
                } else {
                    edtAddress.setText("无");
                }
                /**
                 * 设置电话
                 */
                if (!TextUtils.isEmpty(loginCommonUserBean.getPhone())) {
                    detPhone.setText(loginCommonUserBean.getPhone());
                } else {
                    detPhone.setText("无");
                }


            } else if ("lawyer".equals(type)) {
                /**
                 * 律师用户表
                 */
                LoginLawyerUserBean loginLawyerUserBean = DataSupport.where("userName = ?", userName).findFirst(LoginLawyerUserBean.class);
                /**
                 * 设置用户名
                 */
                if (!TextUtils.isEmpty(userName)) {
                    edtUserName.setText(userName);
                } else {
                    edtUserName.setText("无");
                }
                /**
                 * 设置真实姓名
                 */
                if (!TextUtils.isEmpty(loginLawyerUserBean.getRealName())) {
                    edtRealName.setText(loginLawyerUserBean.getRealName());
                } else {
                    edtRealName.setText("无");
                }
                /**
                 * 设置性别
                 */
                if (!TextUtils.isEmpty(loginLawyerUserBean.getSex())) {
                    edtSex.setText(loginLawyerUserBean.getSex());
                } else {
                    edtSex.setText("无");
                }
                /**
                 * 设置年龄
                 */
                if (!TextUtils.isEmpty(loginLawyerUserBean.getAge())) {
                    edtAge.setText(loginLawyerUserBean.getAge());
                } else {
                    edtAge.setText("无");
                }
                /**
                 * 设置地址
                 */
                if (!TextUtils.isEmpty(loginLawyerUserBean.getAddress())) {
                    edtAddress.setText(loginLawyerUserBean.getAddress());
                } else {
                    edtAddress.setText("无");
                }
                /**
                 * 设置电话
                 */
                if (!TextUtils.isEmpty(loginLawyerUserBean.getPhone())) {
                    detPhone.setText(loginLawyerUserBean.getPhone());
                } else {
                    detPhone.setText("无");
                }
            }
        }


    }

    /**
     * 设置头像
     */
    private void setImage() {
        boolean selectImage = mRealmHelper.selectImage();
        if (selectImage) {
            ImageBean first = DataSupport.where("id = ?", "1").findFirst(ImageBean.class);
            String path = first.getPath();
            imagePicker.getImageLoader().displayImage(this, path, imgImage, 140, 140);
        }
    }

    /**
     * 初始化图片选择器
     */
    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        /**
         * 设置图片缓存方式
         */
        imagePicker.setImageLoader(new GlideImageLoader());

    }

    /**
     * 初始化控件
     */
    private void initView() {
        LinearLayout llImg = findViewById(R.id.ll_img);
        imgImage = findViewById(R.id.img_image);
        edtUserName = findViewById(R.id.edt_user_name);
        edtRealName = findViewById(R.id.edt_real_name);
        edtAge = findViewById(R.id.edt_age);
        edtSex = findViewById(R.id.edt_sex);
        edtAddress = findViewById(R.id.edt_address);
        detPhone = findViewById(R.id.det_phone);
        LinearLayout llPassword = findViewById(R.id.ll_password);
        TextView tvEdit = findViewById(R.id.tv_edit);
        TextView btn = findViewById(R.id.btn);
        TextView tv_titleName = findViewById(R.id.title_name);
        TextView tv_titleReturn = findViewById(R.id.title_return);
        btn.setText("确认修改");
        tv_titleName.setText("修改个人信息");



        tv_titleReturn.setOnClickListener(this);
        tvEdit.setOnClickListener(this);
        llImg.setOnClickListener(this);
        llPassword.setOnClickListener(this);
        btn.setOnClickListener(this);
        mRealmHelper = new RealmHelper();
        initImagePicker();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 开启editText的编辑
             */
            case R.id.tv_edit:
                edtUserName.setInputType(InputType.TYPE_CLASS_TEXT);
                edtRealName.setInputType(InputType.TYPE_CLASS_TEXT);
                edtAge.setInputType(InputType.TYPE_CLASS_TEXT);
                edtSex.setInputType(InputType.TYPE_CLASS_TEXT);
                edtAddress.setInputType(InputType.TYPE_CLASS_TEXT);
                detPhone.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            /**
             * 修改头像
             */
            case R.id.ll_img:
                openImageLoader();
                break;
            /**
             * 修改密码
             */
            case R.id.ll_password:
                break;
            /**
             * 修改用户信息
             */
            case R.id.btn:
                editInformation();
                break;
            case R.id.title_return:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 修改信息
     */
    private void editInformation() {
        String mHelperName = (String) SPUtils.get(this, Constants.REAL_M_HELPER_USERNAME, "");

        if ("common".equals(type)) {
            if (TextUtils.isEmpty(edtUserName.getText().toString().trim())) {
                ToastUtil.shortShow("用户名不能为空，请输入用户名。");
            } else {
                SPUtils.put(this, Constants.USERNAME, edtUserName.getText().toString().trim());
                Log.e("freak","更改信息后的sp："+ SPUtils.get(this,Constants.USERNAME,"").toString());
                LoginCommonUserBean commonUserBean = DataSupport.where("userName = ?", userName).findFirst(LoginCommonUserBean.class);
                mRealmHelper.addUserLogin(type, mHelperName, edtRealName.getText().toString().trim(), commonUserBean.getPassword(), edtSex.getText().toString().trim(), edtAge.getText().toString().trim(),
                        detPhone.getText().toString().trim(), edtAddress.getText().toString().trim());
                RxBus.getDefault().post(new UserEvent(0, edtUserName.getText().toString().trim()));
                ToastUtil.shortShow("修改成功");
//                finish();
            }

        } else if ("lawyer".equals(type)) {
            if (TextUtils.isEmpty(edtUserName.getText().toString().trim())) {
                ToastUtil.shortShow("用户名不能为空，请输入用户名。");
            } else {
                SPUtils.put(this, Constants.USERNAME, edtUserName.getText().toString().trim());
                LoginLawyerUserBean loginLawyerUserBean1 = DataSupport.where("userName = ?", userName).findFirst(LoginLawyerUserBean.class);
                mRealmHelper.addUserLogin(type, mHelperName, edtRealName.getText().toString().trim(), loginLawyerUserBean1.getPassword(), edtSex.getText().toString().trim(), edtAge.getText().toString().trim(),
                        detPhone.getText().toString().trim(), edtAddress.getText().toString().trim());
                RxBus.getDefault().post(new UserEvent(0, edtUserName.getText().toString().trim()));
                ToastUtil.shortShow("修改成功");
//                finish();
            }

        }

    }

    /**
     * 图片选择返回值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                mRealmHelper.addImage(images, images.get(0).path);
                imagePicker.getImageLoader().displayImage(this, images.get(0).path, imgImage, 140, 140);
            } else {
                ToastUtil.shortShow("没有数据");
            }
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
        intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
        //ImagePicker.getInstance().setSelectedImages(images);
        startActivityForResult(intent, 100);
    }
}
