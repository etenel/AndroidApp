package com.androidapp.nethelper;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by etenel on 2017/7/6.
 */

public class NetUtils {
    public static final NetUtils netUtils = new NetUtils();

    public static NetUtils getInstance() {
        return netUtils;
    }

    private NetUtils() {
    }

    public void get(String url, OnHttpClientListener onHttpClientListener) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (onHttpClientListener != null) {
                            onHttpClientListener.onFailure(e.getMessage());
                        }

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null && !response.isEmpty()) {
                            if (onHttpClientListener != null) {
                                onHttpClientListener.onSuccess(response);
                            }
                        }


                    }
                });


    }

    public interface OnHttpClientListener {
        void onSuccess(String response);

        void onFailure(String message);

    }

}
