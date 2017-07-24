package com.androidapp.blescanner.adapter;

import com.androidapp.blescanner.BaseResultCallback;
import com.androidapp.blescanner.BluetoothClient;
import com.androidapp.blescanner.bean.Device;

import java.util.UUID;

import io.reactivex.Observable;

/**
 * Created by etenel on 2017/7/23.
 */

public class BluetoothClientAdapter implements BluetoothClient {
    @Override
    public Observable<Device> search(int millis, boolean cancel) {
        return null;
    }

    @Override
    public void stopSearch() {

    }

    @Override
    public Observable<String> connect(String mac) {
        return null;
    }

    @Override
    public void disconnect(String mac) {

    }

    @Override
    public Observable<String> write(String mac, UUID service, UUID characteristic, byte[] values) {
        return null;
    }

    @Override
    public Observable<String> registerNotify(String mac, UUID service, UUID characteristic, BaseResultCallback<byte[]> callback) {
        return null;
    }

    @Override
    public Observable<String> unRegisterNotify(String mac, UUID service, UUID characteristic) {
        return null;
    }

    @Override
    public void clean(String mac) {

    }

    @Override
    public void cleanAll() {

    }

    @Override
    public void openBluetooth() {

    }

    @Override
    public void closeBluetooth() {

    }
}
