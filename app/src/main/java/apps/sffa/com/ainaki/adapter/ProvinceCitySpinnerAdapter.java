package apps.sffa.com.ainaki.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.Province;
import apps.sffa.com.ainaki.model.Zone;
import apps.sffa.com.ainaki.util.FontManager;

/**
 * Created by mr-code on 5/19/2018.
 */

public class ProvinceCitySpinnerAdapter extends ArrayAdapter<Province> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<Zone> items;
    private final int mResource;
    private Typeface fontIranSans;

    public ProvinceCitySpinnerAdapter(@NonNull Context context, @LayoutRes int resource,
                                      @NonNull List objects) {
        super(context, resource, 0, objects);

        mContext = context;
        fontIranSans = FontManager.getTypeface(mContext, FontManager.IRANSANS_TEXTS);

        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public
    @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        final View view = mInflater.inflate(mResource, parent, false);

        TextView name = (TextView) view.findViewById(R.id.txtProvinceCityName);
        FontManager.setFont(name, fontIranSans);

        Zone data = items.get(position);

        name.setText(data.getName());

        return view;
    }
}
