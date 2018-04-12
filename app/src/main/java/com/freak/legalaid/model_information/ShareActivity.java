package com.freak.legalaid.model_information;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RelativeLayout;

import com.freak.legalaid.R;

import java.io.File;
import java.util.ArrayList;


public class ShareActivity extends Activity implements View.OnClickListener {
    private RelativeLayout mRlShareText, mRlShareSingleimage, mRlShareMultipleimage, mRlShareQQ, mRlShareTencent, mRlShareWechat, mRlShareMicroblog, mRlShareOther;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mContext = this;
        bindView();
    }

    private void bindView() {
        mRlShareText = (RelativeLayout) findViewById(R.id.rl_share_text);
        mRlShareSingleimage = (RelativeLayout) findViewById(R.id.rl_share_singleimage);
        mRlShareMultipleimage = (RelativeLayout) findViewById(R.id.rl_share_multipleimage);

        mRlShareQQ = (RelativeLayout) findViewById(R.id.rl_share_qq);
        mRlShareTencent = (RelativeLayout) findViewById(R.id.rl_share_qqtencent);
        mRlShareWechat = (RelativeLayout) findViewById(R.id.rl_share_wechat);
        mRlShareMicroblog = (RelativeLayout) findViewById(R.id.rl_share_microblog);
        mRlShareOther = (RelativeLayout) findViewById(R.id.rl_share_other);

        mRlShareText.setOnClickListener(new ShareText());
        mRlShareSingleimage.setOnClickListener(new ShareSingleImage());
        mRlShareMultipleimage.setOnClickListener(new ShareMultipleImage());
        mRlShareQQ.setOnClickListener(this);
        mRlShareTencent.setOnClickListener(this);
        mRlShareWechat.setOnClickListener(this);
        mRlShareMicroblog.setOnClickListener(this);
        mRlShareOther.setOnClickListener(this);
    }

    //分享文字至所有第三方软件
    public class ShareText implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra("Kdescription", "分享朋友圈的图片说明");
            intent.putExtra(Intent.EXTRA_TEXT, "这是我的博客地址：https://blog.csdn.net/freak_csh");
            intent.setType("text/plain");
            //设置分享列表的标题，并且每次都显示分享列表
            startActivity(Intent.createChooser(intent, "分享到"));
        }
    }

    //分享单张图片至所有第三方软件
    public class ShareSingleImage implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            String imagePath = Environment.getExternalStorageDirectory() + File.separator + "do1.jpg";
//            //由文件得到uri
//            Uri imageUri = Uri.fromFile(new File(imagePath));
//            Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
//                + getResources().getResourcePackageName(R.drawable.do1) + "/"
//                + getResources().getResourceTypeName(R.drawable.do1) + "/"
//                + getResources().getResourceEntryName(R.drawable.do1));
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.do1);
            Uri imageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),bitmap , null,null));
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra("Kdescription", "分享朋友圈的图片说明");
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "这是我的博客地址：https://blog.csdn.net/freak_csh");

            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, "分享到"));
        }
    }

    //分享多张图片至所有第三方软件
    public class ShareMultipleImage implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ArrayList<Uri> uriList = new ArrayList<>();

            String path = Environment.getExternalStorageDirectory() + File.separator;
            uriList.add(Uri.fromFile(new File(path+"13e277bb0b9c2e3ab90229463357bf40.jpg")));
            uriList.add(Uri.fromFile(new File(path+"869895e73ddd710e8a132afb37461bf0.jpg")));
            uriList.add(Uri.fromFile(new File(path+"4753fc4cd47aa1833c70df4c08f4b7fa.jpg")));
            uriList.add(Uri.fromFile(new File(path+"355ee87cf0ff612331a790f31b3ad113.jpg")));
            uriList.add(Uri.fromFile(new File(path+"ce61ad4d44e3099d87040f38faabbf56.jpg")));

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, "分享到"));
        }
    }

    @Override
    public void onClick(View v) {
        String pakName = "";
        Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送的属性
        intent.setType("text/plain"); // 分享发送的数据类型
        switch (v.getId()) {
            case R.id.rl_share_qq:
                pakName = "com.qzone";  //qq空间
                break;
            case R.id.rl_share_qqtencent:
                pakName = "com.tencent.WBlog"; //腾讯微博
                break;
            case R.id.rl_share_wechat:
                pakName = "com.tencent.mm";  //微信
                break;
            case R.id.rl_share_microblog:
                pakName = "com.sina.weibo";  //微博
                break;
            case R.id.rl_share_other:
                break;
            default:
                break;
        }
        intent.setPackage(pakName);
        intent.putExtra(Intent.EXTRA_TEXT, "这是我的博客地址：https://blog.csdn.net/freak_csh"); // 分享的内容
        this.startActivity(Intent.createChooser(intent, "法律援助app分享"));// 目标应用选择对话框的标题;
    }
}