package apps.sffa.com.ainaki.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import apps.sffa.com.ainaki.R;
import apps.sffa.com.ainaki.util.AndroidUtilities;
import apps.sffa.com.ainaki.widget.PhotoCropView;

/**
 * Created by mr-code on 6/12/2018.
 */

public class PhotoCropActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_crop);

        if (init()) {

            Toast.makeText(getApplicationContext(), "Photo Width: " + imageToCrop.getWidth(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Navi bra", Toast.LENGTH_SHORT).show();

        }

        PhotoCropView photoCropView = (PhotoCropView) findViewById(R.id.photoCrop);
        photoCropView.setBitmapDrawable(drawable);
        photoCropView.setImageToCrop(imageToCrop);
        photoCropView.init(getApplicationContext());

        Intent returnIntent = new Intent();
        returnIntent.putExtra("photoBitmap",photoCropView.getBitmap());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }


    private Bundle getArguments() {
        return getIntent().getExtras();
    }

    private Bitmap loadBitmap(Uri pickedImage) {
        String[] filePath = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
        cursor.moveToFirst();
        String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        cursor.close();
        return bitmap;
    }

    private boolean init() {
        if (imageToCrop == null) {
            String photoPath = getArguments().getString("photoPath");
            Uri photoUri = getArguments().getParcelable("photoUri");
            Toast.makeText(getApplicationContext(), photoPath, Toast.LENGTH_SHORT).show();

            if (photoPath == null && photoUri == null) {
                return false;
            }

            imageToCrop = loadBitmap(photoUri);
            if (imageToCrop == null) {
                return false;
            }
        }
        drawable = new BitmapDrawable(imageToCrop);
        return true;
    }

    private Bitmap imageToCrop;
    private BitmapDrawable drawable;


}
