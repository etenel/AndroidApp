package com.androidapp.util;

import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by etenel on 2017/7/22.
 */

public class DownloadxmlUtils {

    public static void DownloadFiles() {

        try {
            LogUtils.e("开始下载");
            URL url = new URL("http://comment.bilibili.tv/20458.xml");
            URLConnection conexion = url.openConnection();
            conexion.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conexion.setUseCaches(false);
            conexion.setDoInput(true);
            conexion.setDoOutput(true);
//            conexion.setRequestProperty("Content-Length", Integer.toString(soap.length()));
            conexion.connect();
            int lenghtOfFile = conexion.getContentLength();
            InputStream is = conexion.getInputStream();
            // InputStreamReader is = new InputStreamReader(conexion.getInputStream());
            File testDirectory = new File(Environment.getExternalStorageDirectory() + "/Danmaku");
            if (!testDirectory.exists()) {
                testDirectory.mkdir();
                LogUtils.e("创建成功");
            }
            OutputStream outputStream = conexion.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");

            FileOutputStream fos = new FileOutputStream(testDirectory + "/20458.xml");
            byte data[] = new byte[1024];
            String count;
            int total = 0;
            int progress = 0;
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            BufferedWriter writer = null;
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            while ((count = reader.readLine()) != null) {
                count = new String(count.getBytes("ISO-8859-1"), "utf-8");
                writer.write(count);
//                int progress_temp = (int) total * 100 / lenghtOfFile;
//                if (progress_temp % 10 == 0 && progress != progress_temp) {
//                    progress = progress_temp;
//                }
            }
            writer.close();
            reader.close();
            is.close();
            fos.close();
            LogUtils.e("下载完成");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static InputStream getSoapInputStream() throws Exception {
        try {
            // String soap = getSoapRequest();
            String soap = "";
            if (soap == null) {
                return null;
            }
            URL url = new URL("http://comment.bilibili.tv/20458.xml");
            URLConnection conn = url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // conn.setRequestProperty("Content-Length", Integer.toString(soap.length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            OutputStream os = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            //osw.write(new String(soap.getBytes(),"utf-8"));
            File testDirectory = new File(Environment.getExternalStorageDirectory() + "/Danmaku");
            if (!testDirectory.exists()) {
                testDirectory.mkdir();
                LogUtils.e("创建成功");
            }
            OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream(
                    testDirectory + "/weather.xml"));

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                soap += tmp;
                osr.write(soap);
            }
            reader.close();
            // isr.close();
            osw.flush();
            osw.close();
            InputStream is = conn.getInputStream();
            return is;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public  static  void getSoapRequest() {
        URL url = null;
        HttpURLConnection connection = null;
        InputStream in = null;
        OutputStream out = null;
        int responseCode = 0;
        File testDirectory = new File(Environment.getExternalStorageDirectory() + "/Danmaku");
        if (!testDirectory.exists()) {
            testDirectory.mkdir();
            LogUtils.e("创建成功");
        }
        try {
            url = new URL("http://www.baidu.com");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                in = connection.getInputStream();
                out = new BufferedOutputStream(new FileOutputStream(
                        new File(testDirectory, "danmu")));
                byte[] buf = new byte[1024];
                int len = -1;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }
                out.flush();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
