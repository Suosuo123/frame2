package com.hzq.myframe2.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.hzq.myframe2.MainApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CommonUtils {
    public static int dip2pixel(Context context, float n) {
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, n, context.getResources()
                .getDisplayMetrics());
        return value;
    }

    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    public static String getFileNameFromUrl(String url) {
        if (url == null) {
            return "";
        }
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public static void showInputMethod(View view) {
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(view, 0);
    }

    public static void hideInputMethod(Activity activity) {
        InputMethodManager inputMethodManager = ((InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE));
        if (null != inputMethodManager) {
            View view = activity.getCurrentFocus();
            if (null != view) {
                IBinder iBinder = activity.getCurrentFocus().getWindowToken();
                if (null != iBinder) {
                    inputMethodManager.hideSoftInputFromWindow(iBinder, InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
    }


    /**
     * 设置两部分不同颜色字体
     *
     * @param s1
     * @param s1ColorResId
     * @param s2
     * @param s2ColorResId
     * @return
     */
    public static SpannableString setTwoColorText(String s1, int s1ColorResId, String s2, int s2ColorResId) {
        SpannableString ss = new SpannableString(s1 + s2);
        ss.setSpan(new ForegroundColorSpan(MainApplication.getApplication().getResources().getColor(s1ColorResId)), 0,
                s1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(MainApplication.getApplication().getResources().getColor(s2ColorResId)),
                s1.length(), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;

    }

    /**
     * 获取现在时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String getFormatStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * json字符串转MAP
     *
     * @param json
     * @return
     */
    public static HashMap<String, Object> toHashMap(String json) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(json);
            Iterator<?> it = jsonObject.keys();
            // 遍历jsonObject数据，添加到Map对象
            while (it.hasNext()) {
                String key = String.valueOf(it.next());
                Object value = (Object) jsonObject.get(key);
                data.put(key, value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * double保留两位小数
     */
    public static String to2(double f) {
        if (f == 0) {
            return "0.00";
        }
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        DecimalFormat df = new DecimalFormat("#0.00", otherSymbols);
        return df.format(f);
    }

}
