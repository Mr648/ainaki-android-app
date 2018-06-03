package apps.sffa.com.ainaki.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.Lens;
import apps.sffa.com.ainaki.util.FontManager;


/**
 * Created by mr-code on 5/6/2018.
 */

public class LensAdapter extends RecyclerView.Adapter<LensAdapter.ViewHolder> {

    private static Context mContext;
    private ArrayList<Lens> mItems;
    Typeface fontMaterialIcons;

    public LensAdapter(Context mContext,
                       ArrayList<Lens> mItems) {

        this.mItems = mItems;
        this.mContext = mContext;
        fontMaterialIcons = FontManager.getTypeface(mContext, FontManager.MATERIAL_ICONS);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private final ImageView imgFavorite;
        private final ImageView imgShare;
        private final ImageView imgBuy;
        private final ImageView imgLike;
        private final ImageView imgDislike;
        private final TextView txtProductName;
        private final TextView txtType;
        private final TextView txtPrice;


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            imgFavorite = (ImageView) view.findViewById(R.id.imgFavorite);
            imgShare = (ImageView) view.findViewById(R.id.imgShare);
            imgBuy = (ImageView) view.findViewById(R.id.imgBuy);
            imgLike = (ImageView) view.findViewById(R.id.imgLike);
            imgDislike = (ImageView) view.findViewById(R.id.imgDislike);
            txtProductName = (TextView) view.findViewById(R.id.txtProductCategory);
            txtType = (TextView) view.findViewById(R.id.txtType);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);


        }

        public View getView() {
            return view;
        }


        public TextView getTxtProductName() {
            return txtProductName;
        }

        public TextView getTxtType() {
            return txtType;
        }

        public TextView getTxtPrice() {
            return txtPrice;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.favorite_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//
//       FontManager.setFont(holder.getTxtBuy(),fontMaterialIcons);
//       FontManager.setFont(holder.getTxtFavorite(),fontMaterialIcons);
//        FontManager.setFont(holder.getTxtShare(),fontMaterialIcons);
//        FontManager.setFont(holder.getTxtLike(),fontMaterialIcons);
//        FontManager.setFont(holder.getTxtDislike(),fontMaterialIcons);

        holder.getTxtPrice().setText("10000" + " تومان");
        holder.getTxtType().setText("آفتابی");
        holder.getTxtProductName().setText("Rayban DDR4");
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
