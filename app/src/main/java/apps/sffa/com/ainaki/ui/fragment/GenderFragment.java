package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.adapter.ProductCategoryAdapter;
import apps.sffa.com.ainaki.widget.BorderedImageView;


public class GenderFragment extends Fragment implements View.OnTouchListener {

    public GenderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        imgKids = (ImageView) view.findViewById(R.id.imgKids);
        imgMen = (ImageView) view.findViewById(R.id.imgMen);
        imgWomen = (ImageView) view.findViewById(R.id.imgWomen);

        draggableFrameKids = (FrameLayout) view.findViewById(R.id.draggableFrameKids);
        draggableFrameMen = (FrameLayout) view.findViewById(R.id.draggableFrameMen);
        draggableFrameWomen = (FrameLayout) view.findViewById(R.id.draggableFrameWomen);

        ImgOnClickListener imgListener = new ImgOnClickListener();

        imgMen.setColorFilter(getContext().getResources().getColor(R.color.colorPrimaryDark));

        imgKids.setOnClickListener(imgListener);
        imgKids.setTag(Gender.KIDS);
        imgMen.setOnClickListener(imgListener);
        imgMen.setTag(Gender.MEN);
        imgWomen.setOnClickListener(imgListener);
        imgWomen.setTag(Gender.WOMEN);

        animateImageViews(view);


        draggableFrameKids.setOnTouchListener(this);
        draggableFrameMen.setOnTouchListener(this);
        draggableFrameWomen.setOnTouchListener(this);
    }

    float dX;
    float dY;
    int lastAction;

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                view.setY(event.getRawY() + dY);
                view.setX(event.getRawX() + dX);
                lastAction = MotionEvent.ACTION_MOVE;
                break;

            case MotionEvent.ACTION_UP:
                if (lastAction == MotionEvent.ACTION_DOWN)
                    Toast.makeText(getContext(), "Clicked!", Toast.LENGTH_SHORT).show();
                break;

            default:
                return false;
        }
        return true;
    }

    private ImageView imgKids;
    private ImageView imgMen;
    private ImageView imgWomen;

    private FrameLayout draggableFrameKids;
    private FrameLayout draggableFrameMen;
    private FrameLayout draggableFrameWomen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gender, container, false);
    }


    private class ImgOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ImageView img = (ImageView) view;
            mListener.selectGender((Gender) img.getTag());
        }
    }

    ;

    private void animateImageViews(View view) {
        int faders[] = {R.id.border1, R.id.border2, R.id.border3};
        int rotaters[] = {R.id.cimage1, R.id.cimage2, R.id.cimage3};

        Animation alphaAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fadeinout);
        Animation rotateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        Animation rotateInverseAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_inverse);

        int angel = 0;
        for (Integer id : rotaters) {
            BorderedImageView img = (BorderedImageView) view.findViewById(id);
            img.setProgress(80);
            img.setStartAngel(90 * angel++);
            if (id == R.id.cimage2) {
                img.startAnimation(rotateAnimation);
            } else {
                img.startAnimation(rotateInverseAnimation);
            }
        }

        for (Integer id : faders) {
            view.findViewById(id).startAnimation(alphaAnimation);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GenderFragment.GenderFragmentInteraction) {
            mListener = (GenderFragment.GenderFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface GenderFragmentInteraction {
        void selectGender(Gender gender);
    }


}
