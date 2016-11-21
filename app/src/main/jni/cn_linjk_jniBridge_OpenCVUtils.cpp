//
// Created by LinJK on 21/11/2016.
//

#include "cn_linjk_jniBridge_OpenCVUtils.h"

#include <opencv2/opencv.hpp>

#include <Android/log.h>
#include <Android/asset_manager.h>
#include <Android/asset_manager_jni.h>

#define TAG "cn.linjk.opencvtest.jni"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__)

extern "C" {

JNIEXPORT void JNICALL Java_cn_linjk_jniBridge_OpenCVUtils_img2gray
    (JNIEnv *env , jclass objClass, jstring imgFilePath) {

        LOGI("OpenCV version: %s", CV_VERSION);

    }

}

