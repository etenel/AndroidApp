package com.androidapp.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidapp.R;
import com.androidapp.blescanner.BluetoothClient;
import com.androidapp.blescanner.adapter.DeviceAdapter;
import com.androidapp.blescanner.bean.Device;
import com.androidapp.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BleActivity extends AppCompatActivity {
    @BindView(R.id.dummy_button)
    Button dummyButton;
    @BindView(R.id.fullscreen_content_controls)
    LinearLayout fullscreenContentControls;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    private int SCAN_MODE_ERROR = 3;
    private boolean scanModeReceiverRegistered;
    private BluetoothClient client;
    private BroadcastReceiver scanModeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int scanMode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, SCAN_MODE_ERROR);
            if (scanMode == BluetoothAdapter.SCAN_MODE_CONNECTABLE
                    || scanMode == BluetoothAdapter.SCAN_MODE_NONE) {
                Toast.makeText(context, "设备对外不可见", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BleActivity.this, "对外可见", Toast.LENGTH_SHORT).show();
            }
        }
    };
    private DeviceAdapter deviceAdapter;
    public static Context contextOfApplication;
    private List<Device> devices = new ArrayList<>();

    private final Handler handler = new Handler();
    Runnable scanTask = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, 3000);
            scanBluetooth();
        }
    };


    private BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtils.e("bluetoothReceiver:onReceive");
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                LogUtils.e(deviceName);
                boolean paired = device.getBondState() == BluetoothDevice.BOND_BONDED;
                String deviceAddress = device.getAddress();
                LogUtils.e(deviceAddress);
                short deviceRSSI = intent.getExtras().getShort(BluetoothDevice.EXTRA_RSSI, (short) 0);
                Device mDevice = new Device(deviceName, paired, deviceAddress, deviceRSSI);

                devices.remove(scannedDevice(mDevice));
                devices.add(mDevice);
                deviceAdapter.notifyDataSetChanged();
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
//                if (devices.size() == 0) {
//                    LogUtils.e("onReceive: No device");
//                }
                LogUtils.e("ACTION_DISCOVERY_FINISHED");
            }
        }

        private Device scannedDevice(Device d) {
            for (Device device : devices) {
                if (d.getAddress().equals(device.getAddress())) {
                    return device;
                }
            }
            return null;
        }
    };
    public BluetoothManager bluetoothManager;
    private boolean bluetoothReceiverRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "获取权限", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_ENABLE_BT);
            }
        }
        initdata();
        deviceAdapter = new DeviceAdapter(devices);
        recycle.setAdapter(deviceAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.post(scanTask);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        handler.post(scanTask);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bluetoothReceiverRegistered) {
            unregisterReceiver(bluetoothReceiver);
        }
        if (scanModeReceiverRegistered) {
            unregisterReceiver(scanModeReceiver);
        }
    }


    private void initdata() {
        //获取BluetoothAdapter,判断设备是否支持蓝牙
        if (bluetoothManager == null) {
            bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            if (bluetoothManager == null) {
                Toast.makeText(BleActivity.this, "该设备不支持蓝牙", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(BleActivity.this, "该设备不支持蓝牙", Toast.LENGTH_SHORT).show();
            return;
        }
        //检测蓝牙是否开启，尝试开启蓝牙
        if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
            Intent BleIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(BleIntent, REQUEST_ENABLE_BT);
//            Toast.makeText(this, "请返回应用以授权", Toast.LENGTH_LONG).show();
            //注册打开蓝牙检测，
            scanModeReceiverRegistered = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
            registerReceiver(scanModeReceiver, intentFilter);
        }
        scanBluetooth();
    }

    //开始扫描
    private void scanBluetooth() {
//注册监听，接收蓝牙设备信息
        bluetoothReceiverRegistered = true;
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(bluetoothReceiver, filter);
        if (bluetoothAdapter.isDiscovering()) {
            bluetoothAdapter.cancelDiscovery();
        }
        bluetoothAdapter.startDiscovery();
        //client.search(3000, false);
    }

    @OnClick(R.id.dummy_button)
    public void onViewClicked() {
        Intent visibleIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        visibleIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 120);
        startActivity(visibleIntent);
    }

    public static Context getContextOfApplication() {

        return contextOfApplication;
    }
}
