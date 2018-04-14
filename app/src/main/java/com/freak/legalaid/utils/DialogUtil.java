package com.freak.legalaid.utils;

import android.app.Activity;
import android.os.Bundle;

import com.freak.legalaid.app.Constants;
import com.freak.legalaid.dialog.CommonTipsDialogFragment;


/**
 * 弹出框工具
 */

public class DialogUtil {

    /* 标题，内容，确认按钮，取消按钮，按钮监听器 */
    public static void showCommonDialog(Activity activity, String title, String content, String cancel, String sure, CommonTipsDialogFragment.OnTipsListener onTipsListener) {
        CommonTipsDialogFragment dialogFragment = new CommonTipsDialogFragment();
        Bundle args = new Bundle();
        args.putString(Constants.TIPS_TITLE, title);
        args.putString(Constants.TIPS_TEXT, content);
        args.putString(Constants.TIPS_CANCEL, cancel);
        args.putString(Constants.TIPS_SUCCEED, sure);

        dialogFragment.setArguments(args);
        dialogFragment.setCancelable(false);
        dialogFragment.setOnConfirmListener(onTipsListener);
        dialogFragment.show(activity.getFragmentManager(), "");
    }

}
