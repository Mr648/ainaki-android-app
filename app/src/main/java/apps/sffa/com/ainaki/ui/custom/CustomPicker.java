package apps.sffa.com.ainaki.ui.custom;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.CustomPickerListAdapter;

public class CustomPicker extends ConstraintLayout {


    private RecyclerView recItems;
    private AppCompatImageView imgUp;
    private AppCompatImageView imgDown;
    private int currentItem = 648;
    private CustomPickerListAdapter adapter;
    private RecyclerView.SmoothScroller smoothScroller;
    private ArrayList<String> items;

    public CustomPicker(Context context, ArrayList<String> items) {
        super(context);
        this.items = items;
        initialize(context);
    }

    public CustomPicker(Context context, AttributeSet attrs, ArrayList<String> items) {

        super(context, attrs);
        this.items = items;
        initialize(context);
    }

    public CustomPicker(Context context, AttributeSet attrs, int defStyleAttr, ArrayList<String> items) {
        super(context, attrs, defStyleAttr);
        this.items = items;
        initialize(context);
    }


    private void initialize(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_picker, this, true);

        recItems = (RecyclerView) view.findViewById(R.id.recItems);
        imgUp = (AppCompatImageView) view.findViewById(R.id.imgUp);
        imgDown = (AppCompatImageView) view.findViewById(R.id.imgDown);

        smoothScroller = new LinearSmoothScroller(context) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };

        recItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    currentItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    smoothScroller.setTargetPosition(currentItem);
                }
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recItems.setLayoutManager(linearLayoutManager);
        adapter = new CustomPickerListAdapter(context, items);
        recItems.setAdapter(adapter);

//        smoothScroller.setTargetPosition(648);
//
//        linearLayoutManager.startSmoothScroll(smoothScroller);
        recItems.scrollToPosition(currentItem);

        imgUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem--;
                recItems.smoothScrollToPosition(currentItem);
            }
        });

        imgDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem++;
                recItems.smoothScrollToPosition(currentItem);
            }
        });
    }

    public CustomPickerListAdapter getAdapter() {
        return adapter;
    }


    public int getCurrentItem() {
        return currentItem % items.size();
    }

    public void scrollTo(int position) {
        recItems.smoothScrollToPosition(currentItem);
    }
}

