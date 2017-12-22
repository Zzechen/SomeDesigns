package com.zzc.somedesigns.utils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtils {

    private static Toast mToast;

    public static void showToast(Context context, CharSequence text, int duration) {
        if (Looper.myLooper() == null) return;
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(context, text, duration);
        mToast.show();
    }

    public static void showToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

}
