package com.freak.legalaid.model_information;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freak.legalaid.R;
import com.freak.legalaid.app.Constants;
import com.freak.legalaid.bean.ImageBean;
import com.freak.legalaid.event.UserEvent;
import com.freak.legalaid.imageloader.GlideImageLoader;
import com.freak.legalaid.library.net.RealmHelper;
import com.freak.legalaid.library.rxjava.RxBus;
import com.freak.legalaid.utils.SPUtils;
import com.freak.legalaid.utils.ToastUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Administrator on 2018/2/5.
 */

public class InformationFragment extends Fragment implements View.OnClickListener {
    private ImageView me_imageView;
    private TextView me_name;
    private LinearLayout ll_me_account, ll_me_photo, ll_me_collect, ll_me_homepage;
    private LinearLayout ll_my_cqc, ll_my_order, ll_my_alter_information, ll_my_share, ll_my_notebook, ll_my_setting;
    private ImagePicker imagePicker;
    private ArrayList<ImageItem> images = null;
    private RealmHelper mRealmHelper;
    private boolean selectImage;
    private String userName;
    private Subscription subscription;
    private String path;
    private View v_ll_order;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View information = inflater.inflate(R.layout.information_fragment, container, false);
        init(information);
        return information;
    }

    private void init(View view) {
        me_imageView = view.findViewById(R.id.me_imageView);
        me_name = view.findViewById(R.id.me_name);
        ll_me_account = view.findViewById(R.id.ll_me_account);
        ll_me_photo = view.findViewById(R.id.ll_me_photo);
        ll_me_collect = view.findViewById(R.id.ll_me_collect);
        ll_me_homepage = view.findViewById(R.id.ll_me_homepage);
        ll_my_cqc = view.findViewById(R.id.ll_my_cqc);
        ll_my_order = view.findViewById(R.id.ll_my_order);
        v_ll_order = view.findViewById(R.id.v_ll_order);
        ll_my_alter_information = view.findViewById(R.id.ll_my_alter_information);
        ll_my_share = view.findViewById(R.id.ll_my_share);
        ll_my_notebook = view.findViewById(R.id.ll_my_notebook);
        ll_my_setting = view.findViewById(R.id.ll_my_setting);

        me_imageView.setOnClickListener(this);
        ll_me_account.setOnClickListener(this);
        ll_me_photo.setOnClickListener(this);
        ll_me_collect.setOnClickListener(this);
        ll_me_homepage.setOnClickListener(this);
        ll_my_cqc.setOnClickListener(this);
        ll_my_order.setOnClickListener(this);
        ll_my_alter_information.setOnClickListener(this);
        ll_my_share.setOnClickListener(this);
        ll_my_notebook.setOnClickListener(this);
        ll_my_setting.setOnClickListener(this);
        mRealmHelper = new RealmHelper();
        String type = (String) SPUtils.get(getActivity(), Constants.TYPE, "");
        if ("lawyer".equals(type)) {
            v_ll_order.setVisibility(View.VISIBLE);
            ll_my_order.setVisibility(View.VISIBLE);
        } else {
            ll_my_order.setVisibility(View.GONE);
            v_ll_order.setVisibility(View.GONE);
        }

        userName = (String) SPUtils.get(getActivity(), Constants.USERNAME, "");
        if (!TextUtils.isEmpty(userName)) {
            me_name.setText(userName);
        }
        initImagePicker();
        setImage();
        subscription = RxBus.getDefault().tObservable(UserEvent.class).subscribe(new Action1<UserEvent>() {
            @Override
            public void call(UserEvent userEvent) {
                if (userEvent.getId() == 0) {
                    me_name.setText(userEvent.getName());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private void setImage() {
        selectImage = mRealmHelper.selectImage();
        if (selectImage) {
            ImageBean first = DataSupport.where("id = ?", "1").findFirst(ImageBean.class);
            path = first.getPath();
            imagePicker.getImageLoader().displayImage(getActivity(), path, me_imageView, 140, 140);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                mRealmHelper.addImage(images, images.get(0).path);
                imagePicker.getImageLoader().displayImage(getActivity(), images.get(0).path, me_imageView, 140, 140);
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
        Intent intent = new Intent(getActivity().getApplicationContext(), ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
        //ImagePicker.getInstance().setSelectedImages(images);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 设置头像
             */
            case R.id.me_imageView:
                openImageLoader();
                break;
            /**
             * 我的账户
             */
            case R.id.ll_me_account:
                Intent accountIntent = new Intent();
                accountIntent.setClass(getActivity(), MyAccountActivity.class);
                startActivity(accountIntent);
                break;
            /**
             * 我的相册
             */
            case R.id.ll_me_photo:
                break;
            /**
             * 我的收藏
             */
            case R.id.ll_me_collect:
                break;
            /**
             * 我的主页
             */
            case R.id.ll_me_homepage:
                break;
            /**
             * 个人认证
             */
            case R.id.ll_my_cqc:
                break;
            /**
             * 我的订单
             */
            case R.id.ll_my_order:
                Intent orderIntent = new Intent();
                orderIntent.setClass(getActivity(), OrderActivity.class);
                startActivity(orderIntent);
                break;
            /**
             * 修改个人资料
             */
            case R.id.ll_my_alter_information:
                Intent informationIntent = new Intent();
                informationIntent.setClass(getActivity(), AlterInformationActivity.class);
                startActivity(informationIntent);
                break;
            /**
             * 分享
             */
            case R.id.ll_my_share:
                Bitmap bitmap = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.do1);
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
                //微信朋友圈分享
//                ShareUtil.shareImageChina(getActivity(),"com.tencent.mm","com.tencent.mm.ui.tools.ShareToTimeLineUI",uri);
//                所有的第三方软件分享
                Intent intent = new Intent();
                intent.setClass(getActivity(), ShareActivity.class);
                startActivity(intent);
                break;
            /**
             * 使用手册
             */
            case R.id.ll_my_notebook:
                break;
            /**
             * 设置
             */
            case R.id.ll_my_setting:
                Intent settingIntent = new Intent();
                settingIntent.setClass(getActivity(), SettingActivity.class);
                startActivity(settingIntent);
                break;
            default:
                break;

        }
    }
}
