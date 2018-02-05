package com.freak.legalaid.library.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/4.
 */

public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

//    public static void finishOrder() {
//        for (Activity activity : activities) {
//
//            if (activity instanceof WebOrderActivity) {
//                if (!activity.isFinishing()) {
//                    activity.finish();
//                }
//            }
//        }
//    }
}
