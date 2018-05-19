package apps.sffa.com.ainaki.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mr-code on 2/14/2018.
 */

public class FontManager {

    public static final String ROOT = "fonts/";

    public static final String MATERIAL_ICONS = ROOT + "material-icons.ttf";
    public static final String SOCIAL_ICONS = ROOT + "socicon.ttf";
    public static final String IRANSANS_TEXTS = ROOT + "irsans.ttf";


    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

    public static void setFont(final View view, final Typeface typeface) {
        if (view instanceof TextView) {
            ((TextView)view).setTypeface(typeface);
        } else if (view instanceof Button) {
            ((Button)view).setTypeface(typeface);
        }else if (view instanceof EditText) {
            ((EditText)view).setTypeface(typeface);
        }else if (view instanceof TextInputLayout) {
            ((TextInputLayout)view).setTypeface(typeface);
        }else if (view instanceof TextInputEditText) {
            ((TextInputEditText)view).setTypeface(typeface);
        }
    }
    public static void markAsContainer(final View parentView, final Typeface typeface) {
        for (View childView: parentView.getTouchables()) {
            if (childView instanceof TextView) {
                ((TextView)childView).setTypeface(typeface);
            } else if (childView instanceof Button) {
                ((Button)childView).setTypeface(typeface);
            }else if (childView instanceof EditText) {
                ((EditText)childView).setTypeface(typeface);
            }else if (childView instanceof TextInputLayout) {
                ((TextInputLayout)childView).setTypeface(typeface);
            }else if (childView instanceof TextInputEditText) {
                ((TextInputEditText)childView).setTypeface(typeface);
            }
        }
    }
}
