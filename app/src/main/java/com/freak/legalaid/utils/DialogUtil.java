package com.freak.legalaid.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.freak.legalaid.app.Constants;
import com.freak.legalaid.dialog.CommonTipsDialogFragment;

import java.util.Calendar;


/**
 * 弹出框工具
 */

public class DialogUtil {
    private static String date;

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

    /* 显示日期 */
    public static void showBirthDayeDialog(Context context, final TextView resultView) {
        int year, monthOfYear, dayOfMonth;
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        monthOfYear = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                resultView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " 00:00:00");
                date = resultView.getText().toString();
            }
        }, year, monthOfYear, dayOfMonth);

        datePickerDialog.show();
    }
}
