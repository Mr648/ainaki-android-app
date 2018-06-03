package apps.sffa.com.ainaki.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.FAQ;


/**
 * Created by mr-code on 5/6/2018.
 */

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {

    private static  Context mContext;
    private ArrayList<FAQ> mItems;

    public FAQAdapter(Context mContext,
                      ArrayList<FAQ> mItems) {

        this.mItems = mItems;
        this.mContext = mContext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitle;
        private final TextView txtButtonMore;
        private final TextView txtAnswer;
        private final TextView txtDesc;
        private final LinearLayout lnrAnswer;


        public ViewHolder(View view) {
            super(view);
            txtTitle= (TextView) view.findViewById(R.id.txtTitle);
            txtButtonMore= (TextView) view.findViewById(R.id.txtButtonMore);
            txtAnswer= (TextView) view.findViewById(R.id.txtAnswer);
            txtDesc= (TextView) view.findViewById(R.id.txtDesc);
            lnrAnswer= (LinearLayout) view.findViewById(R.id.lnrAnswer);



        }

        public LinearLayout getLnrAnswer() {
            return lnrAnswer;
        }

        public TextView getTxtAnswer() {
            return txtAnswer;
        }

        public TextView getTxtButtonMore() {
            return txtButtonMore;
        }

        public TextView getTxtDesc() {
            return txtDesc;
        }

        public TextView getTxtTitle() {
            return txtTitle;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.faq_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.getTxtTitle().setText(mItems.get(position).getTitle());
        holder.getTxtAnswer().setText(mItems.get(position).getAnswer());
        holder.getTxtDesc().setText(mItems.get(position).getQuestion());
        holder.getTxtButtonMore().setOnClickListener(new View.OnClickListener() {

            boolean collapsed=false;
            @Override
            public void onClick(View view) {
               if(!collapsed){
                   holder.getLnrAnswer().setVisibility(View.VISIBLE);
                   collapsed=!collapsed;
               }
               else {
                   holder.getLnrAnswer().setVisibility(View.GONE);
                   collapsed=!collapsed;

               }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
