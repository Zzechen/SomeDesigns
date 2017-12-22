package com.zzc.somedesigns.loginandperson.person;

import com.zzc.somedesigns.R;

/**
 * @auth zzc
 * @date 2017/12/14
 * @desc ${desc}
 */

class StatusHelper {
    static String desc(int status) {
        String desc = "";
        switch (status) {
            case 1:
                desc = "Pending";
                break;
            case 2:
                desc = "Denied";
                break;
            case 3:
                desc = "Complete";
                break;
            default:
                desc = "Unknow";
                break;
        }
        return desc;
    }

    static int drawable(int status) {
        int resId = 0;
        switch (status) {
            case 1:
                resId = R.drawable.img_pending;
                break;
            case 2:
                resId = R.drawable.img_denied;
                break;
            case 3:
                resId = R.drawable.img_complete;
                break;
            default:
                resId = R.drawable.img_complete;
                break;
        }
        return resId;
    }
}
