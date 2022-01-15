package com.github.bolexueyuan.android.analytics.analytics;

/**
 * Created by chenchangjun on 18/2/9.
 */

 interface OnNetResponseListener  {

    void onPushSuccess();
    void onPushEorr(int errorCode);
    void onPushFailed();



}
