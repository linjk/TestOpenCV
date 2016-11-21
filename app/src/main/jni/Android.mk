LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

OpenCV_INSTALL_MODULES := on
OpenCV_CAMERA_MODULES := off

OPENCV_LIB_TYPE := SHARED

ifeq ("$(wildcard $(OPENCV_MK_PATH))","")
include ../../../../native/jni/OpenCV.mk
else
include $(OPENCV_MK_PATH)
endif

LOCAL_MODULE := testopencv

LOCAL_SRC_FILES := cn_linjk_jniBridge_OpenCVUtils.cpp

LOCAL_LDLIBS +=  -lm -llog -landroid

include $(BUILD_SHARED_LIBRARY)