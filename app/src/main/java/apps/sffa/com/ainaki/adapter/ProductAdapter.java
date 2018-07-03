package apps.sffa.com.ainaki.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.Model;
import apps.sffa.com.ainaki.model.Product;
import apps.sffa.com.ainaki.ui.activity.ShowProductActivity;
import apps.sffa.com.ainaki.util.FontManager;
import apps.sffa.com.ainaki.webservice.API;


/**
 * Created by mr-code on 5/6/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private static Context mContext;
    private ArrayList<Model> mItems;
    Typeface fontMaterialIcons;

    public ProductAdapter(Context mContext,
                          ArrayList<Model> mItems) {

        this.mItems = mItems;
        this.mContext = mContext;
        fontMaterialIcons = FontManager.getTypeface(mContext, FontManager.MATERIAL_ICONS);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private final ImageView imgProduct;
        private final TextView txtProductName;
        private final TextView txtType;
        private final TextView txtPrice;
        private final CardView productContainer;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            imgProduct = (ImageView) view.findViewById(R.id.imgProduct);
            txtProductName = (TextView) view.findViewById(R.id.txtProductCategory);
            txtType = (TextView) view.findViewById(R.id.txtType);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            productContainer = (CardView) view.findViewById(R.id.productContainer);
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

        public CardView getProductContainer() {
            return productContainer;
        }

        public ImageView getImgProduct() {
            return imgProduct;
        }

        public TextView getTxtPrice() {
            return txtPrice;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_item, viewGroup, false);

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

        final Model currentProduct = mItems.get(position);

//        Log.i("PRODUCT", position + " :: " + currentProduct);
//
//        holder.getTxtPrice().setText(String.format("%.3f", currentProduct.getAttrs().get("price")));
//
//        holder.getTxtProductName().setText(currentProduct.getAttrs().get("name").getValue().toString());
//
//        holder.getProductContainer().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, ShowProductActivity.class);
//                intent.putExtra("PRODUCT_ID", currentProduct.getId());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent);
//            }
//        });
//        if (((String[]) currentProduct.getAttrs().get("image").getValue()).length != 0) {
//
//        }
//        try {
//            Picasso.with(mContext).load(API.BASE_URL
//                    + URLDecoder.decode(currentProduct.getImage(), "UTF-8")).into(holder.getImgProduct());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
