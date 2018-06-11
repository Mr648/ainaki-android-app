//
// Created by mr-code on 6/2/2018.
//

#ifndef AINAKIAPP_DETECTIONBASEDTRACKER_JNI_H
#define AINAKIAPP_DETECTIONBASEDTRACKER_JNI_H

#endif //AINAKIAPP_DETECTIONBASEDTRACKER_JNI_H

#include <jni.h>
/* Header for class org_opencv_samples_fd_DetectionBasedTracker */

#ifndef _Included_org_opencv_samples_fd_DetectionBasedTracker
#define _Included_org_opencv_samples_fd_DetectionBasedTracker
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeCreateObject
 * Signature: (Ljava/lang/String;F)J
 */
JNIEXPORT jlong JNICALL Java_apps_sffa_com_ainaki_facedetect_DetectionBasedTracker_nativeCreateObject
        (JNIEnv *, jclass, jstring, jint);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeDestroyObject
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_apps_sffa_com_ainaki_facedetect_DetectionBasedTracker_nativeDestroyObject
        (JNIEnv *, jclass, jlong);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeStart
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_apps_sffa_com_ainaki_facedetect_DetectionBasedTracker_nativeStart
        (JNIEnv *, jclass, jlong);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeStop
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_apps_sffa_com_ainaki_facedetect_DetectionBasedTracker_nativeStop
        (JNIEnv *, jclass, jlong);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeSetFaceSize
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_apps_sffa_com_ainaki_facedetect_DetectionBasedTracker_nativeSetFaceSize
        (JNIEnv *, jclass, jlong, jint);

/*
 * Class:     org_opencv_samples_fd_DetectionBasedTracker
 * Method:    nativeDetect
 * Signature: (JJJ)V
 */
JNIEXPORT void JNICALL Java_apps_sffa_com_ainaki_facedetect_DetectionBasedTracker_nativeDetect
        (JNIEnv *, jclass, jlong, jlong, jlong);

#ifdef __cplusplus
}
#endif
#endif