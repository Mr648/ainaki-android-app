package apps.sffa.com.ainaki.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import apps.sffa.com.ainaki.R;

/**
 * Created by Diako on 22/05/2018.
 */

public class UserProfileActivity extends AppCompatActivity {

    private ImageView imgSelectImageProfile;
    private ImageView imgUserProfileImage;
    private static int LOAD_IMAGE_RESULTS = 1;
    private static int CROP_IMAGE_RESULTS = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        imgSelectImageProfile = (ImageView) findViewById(R.id.imgSelectImageProfile);
        imgUserProfileImage = (ImageView) findViewById(R.id.imgUserProfileImage);

        imgSelectImageProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();

            // Let's read picked image path using content resolver

            Intent intent = new Intent(UserProfileActivity.this, PhotoCropActivity.class);
            intent.putExtra("photoPath", pickedImage.getPath());
            intent.putExtra("photoUri", pickedImage);
            startActivityForResult(intent, CROP_IMAGE_RESULTS);
        }

        if (requestCode == CROP_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            Bitmap userSelectedPhoto = (Bitmap) data.getExtras().get("photoBitmap");

            imgUserProfileImage.setImageBitmap(userSelectedPhoto);
        }
    }
}
