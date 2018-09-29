package apps.sffa.com.ainaki.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Base64;
import android.util.DisplayMetrics;

/**
 * Created by mr-code on 6/12/2018.
 */

public class AndroidUtilities {

    private static Context mContext;

    private float density = 1.0f;

    private static AndroidUtilities utilities;

    private AndroidUtilities(Context context) {
        this.mContext = context;
        density = context.getResources().getDisplayMetrics().density;
    }

    public static AndroidUtilities getInstance(Context context) {
        return utilities == null ? (utilities = new AndroidUtilities(context)) : utilities;
    }


    public int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }


    public static String base64Reverse(String key){
        return Base64.encodeToString(key.getBytes(), Base64.DEFAULT);
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }


}
