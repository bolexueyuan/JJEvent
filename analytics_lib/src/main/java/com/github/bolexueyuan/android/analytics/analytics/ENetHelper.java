package com.github.bolexueyuan.android.analytics.analytics;

import android.content.Context;

import com.github.bolexueyuan.android.analytics.analytics.bean.EventBean;
import com.github.bolexueyuan.android.analytics.analytics.bean.ResultBean;
import com.github.bolexueyuan.android.analytics.analytics.net.core.Request;
import com.github.bolexueyuan.android.analytics.analytics.net.core.RequestQueue;
import com.github.bolexueyuan.android.analytics.analytics.net.core.Response;
import com.github.bolexueyuan.android.analytics.analytics.net.core.Tools.EVolley;
import com.github.bolexueyuan.android.analytics.analytics.net.gson.EGson;
import com.github.bolexueyuan.android.analytics.analytics.net.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络模块, 网络不好,需要缓存到本地.
 * Created by chenchangjun on 18/2/8.
 */

 class ENetHelper {

    private static ENetHelper ENetHelper;
    private static boolean isLoading = false;
    private static OnNetResponseListener responseListener;
    private RequestQueue queue;

    public static ENetHelper create(Context context, OnNetResponseListener responseListener) {

        if (ENetHelper == null) {
            synchronized (ENetHelper.class) {//双重检查
                if (ENetHelper == null) {
                    ENetHelper = new ENetHelper(context, responseListener);

                }
            }
        }
        return ENetHelper;
    }

    private ENetHelper(Context context, OnNetResponseListener responseListener) {
        ENetHelper.responseListener = responseListener;
        queue = EVolley.newRequestQueue(context);

    }


    public void sendEvent(String style, List<EventBean> list) {

        switch (style) {

            case EConstant.EVENT_TYPE_PV:
                loadData(list);
                break;
            case EConstant.EVENT_TYPE_EVENT:
                loadData(list);
                break;
            case EConstant.EVENT_TYPE_EXPOSE:
                loadData(list);
                break;
            default:
                loadData(list);

                break;
        }

    }


    public void loadData(List<EventBean> list) {


        isLoading = true;

        EGson EGson = new GsonBuilder().disableHtmlEscaping().create();
        Map map = new HashMap();
        map.put("list", EGson.toJson(list));
        ELogger.logWrite(EConstant.TAG, "push map-->" + map);


        EGsonRequest request = new EGsonRequest<>(Request.Method.POST, EConstant.COLLECT_URL, ResultBean.class, null, map,//191
                (Response.Listener<ResultBean>) response -> {
                    int code = response.getError_code();
                    String msg = "";
                    ELogger.logWrite(EConstant.TAG, response.toString());

                    if (code == 0) {
                        responseListener.onPushSuccess();
                        ELogger.logWrite(EConstant.TAG, "--onPushSuccess--");

                    } else {
                        responseListener.onPushEorr(code);
                        ELogger.logWrite(EConstant.TAG, "--onPushEorr--");

                    }

                    isLoading = false;

                },
                error -> {
                    ELogger.logWrite(EConstant.TAG, "--onVolleyError--");
                    responseListener.onPushFailed();
                    isLoading = false;
                }
        );
        queue.add(request);


    }


    public static boolean getIsLoading() {
        return isLoading;
    }


}
