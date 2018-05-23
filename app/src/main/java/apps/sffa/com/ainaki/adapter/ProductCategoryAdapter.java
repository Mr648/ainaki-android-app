package apps.sffa.com.ainaki.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.util.FontManager;


/**
 * Created by mr-code on 5/6/2018.
 */

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder> {

    private static Context mContext;
    private ArrayList<String> mItems;
    Typeface fontIransans;

    public ProductCategoryAdapter(Context mContext,
                                  ArrayList<String> mItems) {

        this.mItems = mItems;
        this.mContext = mContext;
        fontIransans = FontManager.getTypeface(mContext, FontManager.IRANSANS_TEXTS);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final View view;

        private final LinearLayout btnMore;
        private final TextView txtProductCategory;
        private final RecyclerView recProducts;


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            btnMore = (LinearLayout) view.findViewById(R.id.btnMore);
            txtProductCategory = (TextView) view.findViewById(R.id.txtProductCategory);
            recProducts = (RecyclerView) view.findViewById(R.id.recProducts);
        }

        public View getView() {
            return view;
        }

        public LinearLayout getBtnMore() {
            return btnMore;
        }

        public TextView getTxtProductCategory() {
            return txtProductCategory;
        }

        public RecyclerView getRecProducts() {
            return recProducts;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_recycler_item, viewGroup, false);

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


        holder.getTxtProductCategory().setText(mItems.get(position));


        holder.getRecProducts().setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        ProductMiniItemAdapter adapter = new ProductMiniItemAdapter(mContext,initProductItems());
        holder.getRecProducts().setAdapter(adapter);

    }
    private ArrayList<String> initProductItems(){
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList("Product #1","Product #2","Product #3","Product #4","Product #5","Product #6"));
        return list;
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
