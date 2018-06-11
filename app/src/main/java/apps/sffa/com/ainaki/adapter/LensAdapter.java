package apps.sffa.com.ainaki.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.Lens;
import apps.sffa.com.ainaki.util.FontManager;
import apps.sffa.com.ainaki.widget.BorderedImageView;


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
        private final ImageView imgBuy;
        private final TextView txtProductName;
        private final TextView txtType;
        private final TextView txtPrice;


        private final AppCompatImageView imgFace;
        private final AppCompatImageView imgSmile;
        private final AppCompatImageView imgLeftEye;
        private final AppCompatImageView imgRightEye;

        private void animateImageViews(View view) {

            Animation alphaAnimation = AnimationUtils.loadAnimation(mContext, R.anim.fadeinout);
            view.findViewById(R.id.imgBorder).startAnimation(alphaAnimation);
            view.findViewById(R.id.txtTest).startAnimation(alphaAnimation);

        }
        private ViewHolder(View view) {
            super(view);
            this.view = view;
            imgFavorite = (ImageView) view.findViewById(R.id.imgFavorite);
            imgBuy = (ImageView) view.findViewById(R.id.imgBuy);
            txtProductName = (TextView) view.findViewById(R.id.txtProductCategory);
            txtType = (TextView) view.findViewById(R.id.txtType);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);

            imgFace = (AppCompatImageView) view.findViewById(R.id.imgFace);
            imgSmile = (AppCompatImageView) view.findViewById(R.id.imgSmile);
            imgLeftEye = (AppCompatImageView) view.findViewById(R.id.imgLeftEye);
            imgRightEye = (AppCompatImageView) view.findViewById(R.id.imgRightEye);


            Timer timer = new Timer();
            final Handler handler = new Handler();
            final TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            imgLeftEye.setColorFilter(getRandomColor());
                            imgRightEye.setColorFilter(getRandomColor());
                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(timerTask, 150, 150);
            animateImageViews(view);

        }

        private int getRandomColor() {
            return Color.argb(255,
                    ((int) (Math.random() * 255)),
                    ((int) (Math.random() * 255)),
                    ((int) (Math.random() * 255)));
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
                .inflate(R.layout.lens_list_item, viewGroup, false);

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
