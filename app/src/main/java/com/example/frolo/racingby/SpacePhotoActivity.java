package com.example.frolo.racingby;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class SpacePhotoActivity extends AppCompatActivity {

    public static final String EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   this.setTitle("Галерея");
        setContentView(R.layout.activity_photo_detail);
        mImageView = (ImageView) findViewById(R.id.image);
        SpacePhoto spacePhoto = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);

     /*   View decorView = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        } */

        Glide.with(this)
                .load(spacePhoto.getUrl())
                .asBitmap()
                .error(R.drawable.ic_launcher_background)
                .listener(new RequestListener<String, Bitmap>() {

                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target,
                                                   boolean isFromMemoryCache, boolean isFirstResource) {

                        onPalette(Palette.from(resource).generate());
                        mImageView.setImageBitmap(resource);

                        return false;
                    }

                    @SuppressLint("ResourceAsColor")
                    public void onPalette(Palette palette) {
                            ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                            parent.setBackgroundColor(getResources().getColor(R.color.colorBackground));
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mImageView);
    }
}
