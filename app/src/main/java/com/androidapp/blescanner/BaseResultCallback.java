package com.androidapp.blescanner;

import com.baidu.platform.comapi.map.D;

/**
 * Created by etenel on 2017/7/23.
 * 用于数据回传的基本接口
 */

public interface BaseResultCallback<T> {
    /**
     * 成功拿到数据
     *
     * @param data 回传的数据
     */
    void onSuccess(D data);

    /**
     * 操作失败
     *
     * @param msg 失败的返回的异常信息
     */
    void onFail(String msg);
}
