package cn.linjk.opencvtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.linjk.jniBridge.OpenCVUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OpenCVUtils.img2gray("");
    }
}
