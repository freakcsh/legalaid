package com.freak.legalaid.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

/**
 *   国内和国外几大社交app的类名和包名
 *   "微信", "com.tencent.mm.ui.tools.ShareImgUI", "com.tencent.mm"
 *    "朋友圈",  "com.tencent.mm.ui.tools.ShareToTimeLineUI", "com.tencent.mm"
 *     "qq", "com.tencent.mobileqq.activity.JumpActivity","com.tencent.mobileqq"
 *      "qq空间",  "com.qzone.ui.operation.QZonePublishMoodActivity","com.qzone"
 *       "新浪微博", "com.sina.weibo.EditActivity", "com.sina.weibo" "com.sina.weibog3"
 *        "腾讯微博",  "com.tencent.WBlog.intentproxy.TencentWeiboIntent","com.tencent.WBlog"
 *         "FB" "com.facebook.katana"
 *          "MS" "com.instagram.android"
 *           "Ins" "com.facebook.orca"
  */
public class ShareUtil {
    /**
     *国外社交的分享
     *      由于国外的Ins,FB,MS几大app,通过意图过滤得到的要传递发送图片的Activity都只有一个,即分享界面的Activity,故这里只需要包名即可
     * @param activity
     * @param packageName 要分享的国外的社交app(Ins,FB,MS)
     * @param fileUri 分享图片的Uri
     */
    public static void shareImageForeign(Activity activity,String packageName,Uri fileUri){
        Intent mIntent = new Intent(Intent.ACTION_SEND);
        mIntent.setType("image/*");
        mIntent.putExtra(Intent.EXTRA_SUBJECT, "shareImage");
        mIntent.setPackage(packageName);
        mIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        try {
            activity.startActivity(Intent.createChooser(mIntent, activity.getTitle()));
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(activity, "To be share,please install this software: " + packageName,
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 国内几大社交的分享
     *      由于国内几大社交app(QQ,空间,微信,朋友圈,微博等),通过意图过滤得到的要传递图片的Activity不只一个(例如QQ会出现发送,发送到电脑;微信会出现发送和收藏),故这里还需要制定具体分享
     *      界面的全类名,才能实现快速分享
     * @param context
     * @param packageName 要分享的社交app的包名
     * @param shareActivityName 分享界面Activity的全类名
     * @param fileUri 分享图片的Uri
     */
    public static void  shareImageChina(Activity context,String packageName, String shareActivityName , Uri fileUri){
        ComponentName componentName=new ComponentName(packageName,shareActivityName);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra("Kdescription", "分享朋友圈的图片说明");
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
//        intent.putExtra(Intent.EXTRA_SUBJECT, "shareImage");
//        intent.setClassName(packageName,shareActivityName);
//        context.startActivity(Intent.createChooser(intent,"分享到"));
//        context.startActivity(Intent.createChooser(intent, context.getTitle()));
        context.startActivity(intent);
    }

    /**
     * 保存视频文件到本地相册,并生成缩略图,避免分享时没有缩略图而显示为黑色
     * @param path
     */
    public static void saveSdScan(String path,Context context){
//        final Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.MICRO_KIND); //生成缩略图
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("_data", path);
        localContentValues.put("description", "save video ---");
        localContentValues.put("mime_type", "video/mp4");
        ContentResolver localContentResolver = context.getContentResolver();
        Uri localUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        localContentResolver.insert(localUri, localContentValues);
//                MediaStore.Images.Media.insertImage(localContentResolver,bitmap,"thumbnail","thumbnail for video"); //这个是给图片添加缩略图

    }

    /**
     * 国外视频的分享
     *   国外app(Ins,FB,MS)支持直接分享视频的
     * @param activity
     * @param packageName
     * @param fileUri
     */
    public static void shareVideoForeign(Activity activity, String packageName, Uri fileUri){
        //保存视频到相册以便分享
        saveSdScan(fileUri.getPath(), activity);

        Intent mIntent = new Intent(Intent.ACTION_SEND);
        mIntent.setType("video/*");
        mIntent.putExtra(Intent.EXTRA_SUBJECT, "shareVideo");
        mIntent.setPackage(packageName);
        mIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
        try {
            activity.startActivity(Intent.createChooser(mIntent, activity.getTitle()));
        } catch (android.content.ActivityNotFoundException e) {
            Toast.makeText(activity, "To be share,please install this software: " + packageName,
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *  国内视频的分享
     *       目前国内只支持QQ,微信的直接发送视频,其他的(空间,朋友圈,微博)都需要借助于服务器,需要上传到服务器上,再将Url分享出去
     * @param context
     * @param packageName
     * @param shareActivityName
     * @param fileUri
     */
    public static void  shareVideoChina(Context context, String packageName, String shareActivityName , Uri fileUri){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("video/*");
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
        intent.putExtra(Intent.EXTRA_SUBJECT, "shareVideo");
        intent.putExtra(Intent.EXTRA_TITLE, "视频标题");
        intent.setComponent((new ComponentName("packageName", "ActivityName")));
        context.startActivity(intent);
    }
}
