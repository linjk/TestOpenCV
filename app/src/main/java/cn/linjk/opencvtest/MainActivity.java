package cn.linjk.opencvtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.linjk.jniBridge.OpenCVUtils;

public class MainActivity extends AppCompatActivity {

    private Button btnOpenCamera;
    private ImageView ivImgOutput;
    private Button btnImgGray;

    private String imageFilePath;

    private static final int CAMERA_RESULT = 1112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenCamera = (Button)findViewById(R.id.btn_open_camera);
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                openCamera();
            }
        });

        ivImgOutput = (ImageView)findViewById(R.id.img_output);

        btnImgGray   = (Button)findViewById(R.id.img_gray);
        btnImgGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                OpenCVUtils.img2gray(imageFilePath);
                //
                BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
                bmpFactoryOptions.inJustDecodeBounds = true;
                Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);

                bmpFactoryOptions.inSampleSize = calculateInSampleSize(bmpFactoryOptions, 1280, 800);

                bmpFactoryOptions.inJustDecodeBounds = false;

                bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);

                ivImgOutput.setImageBitmap(bmp);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
            bmpFactoryOptions.inJustDecodeBounds = true;
            Bitmap bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);

            bmpFactoryOptions.inSampleSize = calculateInSampleSize(bmpFactoryOptions, 1280, 800);

            bmpFactoryOptions.inJustDecodeBounds = false;

            bmp = BitmapFactory.decodeFile(imageFilePath, bmpFactoryOptions);

            ivImgOutput.setImageBitmap(bmp);

            saveBitmap(bmp);
        }
    }

    private void openCamera() {
        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/testImage.jpg";
        File imageFile = new File(imageFilePath);
        Uri imageFileUri = Uri.fromFile(imageFile);

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(i, CAMERA_RESULT);
    }

    private int calculateInSampleSize(BitmapFactory.Options options,
                                      int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
        }

        return inSampleSize;
    }

    private void saveBitmap(Bitmap bm) {
        File f = new File(imageFilePath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
