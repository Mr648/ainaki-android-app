package apps.sffa.com.ainaki.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.Model;
import apps.sffa.com.ainaki.util.FontManager;


/**
 * Created by mr-code on 5/6/2018.
 */

public class CustomPickerListAdapter extends RecyclerView.Adapter<CustomPickerListAdapter.ViewHolder> {

    private static Context mContext;
    private ArrayList<String> mItems;
    Typeface fontIranSans;

    public CustomPickerListAdapter(Context mContext,
                                   ArrayList<String> mItems) {

        this.mItems = mItems;
        this.mContext = mContext;
        fontIranSans = FontManager.getTypeface(mContext, FontManager.IRANSANS_TEXTS);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtContent;

        public ViewHolder(View view) {
            super(view);
            txtContent = (TextView) view.findViewById(R.id.txtContent);
        }

        public TextView getTxtContent() {
            return txtContent;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_picker_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        FontManager.setFont(holder.getTxtContent(), fontIranSans);

        int positionInList = position % mItems.size();
        holder.getTxtContent().setText(mItems.get(positionInList));

    }


    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


}
