package apps.sffa.com.ainaki.util;

import android.content.Context;
import android.os.Build;

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

}
