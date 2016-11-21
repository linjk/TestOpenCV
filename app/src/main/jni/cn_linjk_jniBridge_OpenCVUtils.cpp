//
// Created by LinJK on 21/11/2016.
//

#include "cn_linjk_jniBridge_OpenCVUtils.h"

#include <opencv2/opencv.hpp>
#include <string>
#include <iostream>

#include <Android/log.h>
#include <Android/asset_manager.h>
#include <Android/asset_manager_jni.h>

#define TAG "cn.linjk.opencvtest.jni"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__)

using namespace std;
using namespace cv;

class ImageUtils{
public:
    void imageToGray(Mat inputImg, string outFilePath);
};

void ImageUtils::imageToGray(Mat inputImg, string outFilePath) {
    Mat gray;

    Mat input = inputImg.clone();

    cvtColor(input, gray, COLOR_BGR2GRAY);

    imwrite(outFilePath,  gray);
}

extern "C" {

JNIEXPORT void JNICALL Java_cn_linjk_jniBridge_OpenCVUtils_img2gray
    (JNIEnv *env , jclass objClass, jstring imgFilePath) {

        LOGI("OpenCV version: %s", CV_VERSION);

        char buf[128];
        const char *str = env->GetStringUTFChars(imgFilePath, 0);
        LOGD("图像路径: %s", str);

        Mat img = imread(str);
        if (!img.data) {
            LOGE("-----CV: 读取相片数据出错");
        }
        else {
            LOGD("-----CV: 读取相片数据成功");
            ImageUtils().imageToGray(img, str);
        }
    }

}

