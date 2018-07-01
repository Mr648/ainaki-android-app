package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.model.Gender;
import apps.sffa.com.ainaki.widget.BorderedImageView;
import butterknife.ButterKnife;

/**
 * Created by Diako on 10/06/2018.
 */

public class GenderActivity extends AppCompatActivity implements View.OnTouchListener {
    float dX;
    float dY;
    int lastAction;

    private ImageView imgKids;
    private ImageView imgMen;
    private ImageView imgWomen;

    private FrameLayout draggableFrameKids;
    private FrameLayout draggableFrameMen;
    private FrameLayout draggableFrameWomen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        ButterKnife.bind(this);
        imgKids = (ImageView) findViewById(R.id.imgKids);
        imgMen = (ImageView) findViewById(R.id.imgMen);
        imgWomen = (ImageView) findViewById(R.id.imgWomen);

        draggableFrameKids = (FrameLayout) findViewById(R.id.draggableFrameKids);
        draggableFrameMen = (FrameLayout) findViewById(R.id.draggableFrameMen);
        draggableFrameWomen = (FrameLayout) findViewById(R.id.draggableFrameWomen);

        ImgOnClickListener imgListener = new ImgOnClickListener();

        imgMen.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorPrimaryDark));

        imgKids.setOnClickListener(imgListener);
        imgKids.setTag(Gender.KIDS);
        imgMen.setOnClickListener(imgListener);
        imgMen.setTag(Gender.MEN);
        imgWomen.setOnClickListener(imgListener);
        imgWomen.setTag(Gender.WOMEN);

        animateImageViews();


        draggableFrameKids.setOnTouchListener(this);
        draggableFrameMen.setOnTouchListener(this);
        draggableFrameWomen.setOnTouchListener(this);

    }

    private void animateImageViews() {
        int faders[] = {R.id.border1, R.id.border2, R.id.border3};
        int rotaters[] = {R.id.cimage1, R.id.cimage2, R.id.cimage3};

        Animation alphaAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeinout);
        Animation rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        Animation rotateInverseAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_inverse);

        int angel = 0;
        for (Integer id : rotaters) {
            BorderedImageView img = (BorderedImageView) findViewById(id);
            img.setProgress(80);
            img.setStartAngel(90 * angel++);
            if (id == R.id.cimage2) {
                img.startAnimation(rotateAnimation);
            } else {
                img.startAnimation(rotateInverseAnimation);
            }
        }

        for (Integer id : faders) {
            findViewById(id).startAnimation(alphaAnimation);
        }

    }

    private class ImgOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ImageView img = (ImageView) view;
            Intent intent = new Intent(GenderActivity.this, MainActivity.class);
            intent.putExtra("GENDER", ((Gender) img.getTag()));
            startActivity(intent);
        }
    }

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
                    Toast.makeText(getApplicationContext(), "Clicked!", Toast.LENGTH_SHORT).show();
                break;

            default:
                return false;
        }
        return true;
    }
}
