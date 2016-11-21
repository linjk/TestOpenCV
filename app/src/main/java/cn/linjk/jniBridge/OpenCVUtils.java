package cn.linjk.jniBridge;

/**
 * Created by LinJK on 21/11/2016.
 */

public class OpenCVUtils {

    static {
        System.loadLibrary("testopencv");
    }

    public static native void img2gray(String imgPath);

}
