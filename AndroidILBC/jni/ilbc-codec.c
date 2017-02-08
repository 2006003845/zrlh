#include <jni.h>

#include <math.h>
#include <string.h>
#include "iLBC_define.h"
#include "iLBC_decode.h"
#include "iLBC_encode.h"

#define LOG_TAG "ilbc-codec"

#ifdef BUILD_FROM_SOURCE
#include <utils/Log.h>
#else
#include <android/log.h>
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG, __VA_ARGS__) 
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG  , LOG_TAG, __VA_ARGS__) 
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO   , LOG_TAG, __VA_ARGS__) 
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN   , LOG_TAG, __VA_ARGS__) 
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR  , LOG_TAG, __VA_ARGS__) 

#endif

//编解码时用到的两个关键的数据结构
static iLBC_Enc_Inst_t g_enc_inst;
static iLBC_Dec_Inst_t g_dec_inst;

/* 编码一次
*/
static int encode(short *samples, unsigned char *data)
{
    int i;
    float block[BLOCKL_MAX];

    // Convert to float representaion of voice signal.
    for (i = 0; i < g_enc_inst.blockl; i++) {
        block[i] = samples[i];
    }

    iLBC_encode(data, block, &g_enc_inst);

    return g_enc_inst.no_of_bytes;
}
/*解码一次
*/
static int decode(unsigned char *data, short *samples, int mode)
{
    int i;
    float block[BLOCKL_MAX];

    // mode:30,20,15
    if (mode != 0 && mode != 1) {
        LOGE("Bad mode");
        return -1;
    }

    iLBC_decode(block, data, &g_dec_inst, mode);

    //  PCM16
    for (i = 0; i < g_dec_inst.blockl; i++) {
        float point;

        point = block[i];
        if (point < MIN_SAMPLE) {
            point = MIN_SAMPLE;
        } else if (point > MAX_SAMPLE) {
            point = MAX_SAMPLE;
        }

        samples[i] = point;
    }

    return g_dec_inst.blockl * 2;
}

jint Java_com_googlecode_androidilbc_Codec_init(
        JNIEnv *env, jobject this, jint mode)
{
    initEncode(&g_enc_inst, mode);
    initDecode(&g_dec_inst, mode, 1);
}

/**  raw---->amr
 *JNI 函数，和Java层的代码包名对应
  从Java层传入一段采样的音频，将其编码成AMR
  sampleArray:采集的一段音频数据
  sampleOffset:采样数据偏移量
  sampleLength:音频数据长度
  dataArray：编码后的数据，返回给Java层让其处理（比如使用UDP 发送给另一方）
  dataOffset:偏移量；
  return：编码后的数据长度（采集960B的原始数据，编码成AMR后则返回100B）
 */
jint Java_com_googlecode_androidilbc_Codec_encode(
        JNIEnv *env, jobject this,
        jbyteArray sampleArray, jint sampleOffset, jint sampleLength,
        jbyteArray dataArray, jint dataOffset)
{
    jsize samples_sz, data_sz;
    jbyte *samples, *data;
    int bytes_to_encode;//剩余的未编码长度
    int bytes_encoded;//一编码长度

	//Java和C语言之间的数据类型转换
    samples_sz = (*env)->GetArrayLength(env, sampleArray);
    samples = (*env)->GetByteArrayElements(env, sampleArray, 0);
    data_sz= (*env)->GetArrayLength(env, dataArray);
    data = (*env)->GetByteArrayElements(env, dataArray, 0);

    samples += sampleOffset;
    data += dataOffset;

    bytes_to_encode = sampleLength;
    bytes_encoded = 0;

    int truncated = bytes_to_encode % (g_enc_inst.blockl * 2);
    if (!truncated) {
        LOGW("Ignore last %d bytes", truncated);
        bytes_to_encode -= truncated;
    }

    while (bytes_to_encode > 0) {

        int _encoded;

        _encoded = encode((short *)samples, data);

        samples += g_enc_inst.blockl * 2;
        data += _encoded;

        bytes_encoded += _encoded;
        bytes_to_encode -= g_enc_inst.blockl * 2;
    }

	//将数组指针移到开头
    samples -= sampleLength;
    data -= bytes_encoded;

	//释放类型转换的资源
    (*env)->ReleaseByteArrayElements(env, sampleArray, samples, 0);
    (*env)->ReleaseByteArrayElements(env, dataArray, data, 0);

    return bytes_encoded;
}

/** amr--->raw
 *JNI 函数
  从Java层传入一段已经编码的AMR音频，进行解码
  dataArray：编码后的数据
  dataOffset:偏移量；
  dataLength:AMR音频数据长度
  sampleArray:解码后的音频流，返回给Java进行播放（使用AudioTracker可以播放音频流）
  sampleOffset:偏移量
  
  return：解码后的数据长度
  比如：传入一段100B的编码后的AMR格式音频数据，解码后返回长度为960B的原始音频流
 */
jint Java_com_googlecode_androidilbc_Codec_decode(
        JNIEnv *env, jobject this,
        jbyteArray dataArray, jint dataOffset, jint dataLength,
        jbyteArray sampleArray, jint sampleOffset)
{
    jsize samples_sz, data_sz;
    jbyte *samples, *data;
    int bytes_to_decode, bytes_decoded;

    samples_sz = (*env)->GetArrayLength(env, sampleArray);
    samples = (*env)->GetByteArrayElements(env, sampleArray, 0);
    data_sz= (*env)->GetArrayLength(env, dataArray);
    data = (*env)->GetByteArrayElements(env, dataArray, 0);

    samples += sampleOffset;
    data += dataOffset;

    bytes_to_decode = dataLength;
    bytes_decoded = 0;

    while (bytes_to_decode > 0) {

        int _decoded;

        _decoded = decode(data, (short *)samples, 1);

        samples += _decoded;
        data += g_dec_inst.no_of_bytes;

        bytes_decoded += _decoded;
        bytes_to_decode -= g_dec_inst.no_of_bytes;
    }

    samples -= bytes_decoded;
    data -= dataLength;

    (*env)->ReleaseByteArrayElements(env, sampleArray, samples, 0);
    (*env)->ReleaseByteArrayElements(env, dataArray, data, 0);

    return bytes_decoded;
}
