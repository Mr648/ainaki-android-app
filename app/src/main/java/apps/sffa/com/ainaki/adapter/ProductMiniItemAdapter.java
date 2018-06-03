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
import apps.sffa.com.ainaki.util.FontManager;


/**
 * Created by mr-code on 5/6/2018.
 */

public class ProductMiniItemAdapter extends RecyclerView.Adapter<ProductMiniItemAdapter.ViewHolder> {

    private static Context mContext;
    private ArrayList<String> mItems;
    Typeface fontIransans;

    public ProductMiniItemAdapter(Context mContext,
                                  ArrayList<String> mItems) {

        this.mItems = mItems;
        this.mContext = mContext;
        fontIransans = FontManager.getTypeface(mContext, FontManager.IRANSANS_TEXTS);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;
        private final ImageView imgProduct;
        private final TextView txtProductName;


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
            txtProductName = (TextView) view.findViewById(R.id.txtProductCategory);
        }

        public View getView() {
            return view;
        }

        public ImageView getImgProduct() {
            return imgProduct;
        }

        public TextView getTxtProductName() {
            return txtProductName;
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item_mini, viewGroup, false);

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


        holder.getImgProduct().setImageResource(drawables[((int) (Math.random() * 3))]);
        holder.getTxtProductName().setText(mItems.get(position));
    }

    int[] drawables = {R.drawable.cleaner_1, R.drawable.cleaner_2, R.drawable.cleaner_3, R.drawable.eyeglass};

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
