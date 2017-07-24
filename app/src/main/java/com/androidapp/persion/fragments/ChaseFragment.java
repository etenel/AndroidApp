package com.androidapp.persion.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androidapp.R;
import com.androidapp.base.BaseFragment;
import com.androidapp.nethelper.NetUtils;
import com.androidapp.persion.adapter.ChaseAdapter;
import com.androidapp.persion.bean.Bilibili;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by etenel on 2017/7/20.
 */

public class ChaseFragment extends BaseFragment {
    String url1 = "http://api.bilibili.com/online_list?_device=android&platform=android&typeid=0&sign=a520d8d8f7a7240013006e466c8044f7";
    String url = "http://api.bilibili.com/online_list?_device=android&platform=android&typeid=13&sign=a520d8d8f7a7240013006e466c8044f7";
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private ChaseAdapter adapter;
    private List<Bilibili.ListBean> listBeen;

    @Override
    public void initData() {

        NetUtils.getInstance().get(url, new NetUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String response) {

                proessData(response);

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void proessData(String response) {
        Bilibili bilibili = new Bilibili();
        try {
            JSONObject jsonObject = new JSONObject(response);
            int code = jsonObject.optInt("code");
            bilibili.setCode(code);
            JSONObject jsonList = jsonObject.optJSONObject("list");
            Log.e("TAG", "jsonlist" + jsonList);
            if (jsonList != null) {
                listBeen = new ArrayList<>();
                Log.e("TAG", "length=" + jsonList.length());
                Log.e("TAG", listBeen.toString());
                bilibili.setList(listBeen);
                for (int i = 0; i < jsonList.length(); i++) {
                    JSONObject dataobject = jsonList.optJSONObject("" + i);
                    if (dataobject != null) {
                        Bilibili.ListBean listEntity = new Bilibili.ListBean();
                        String aid = dataobject.optString("aid");
                        listEntity.setAid(aid);
                        String author = dataobject.optString("author");
                        listEntity.setAuthor(author);
                        String create = dataobject.optString("create");
                        listEntity.setCreate(create);
                        String description = dataobject.optString("description");
                        listEntity.setDescription(description);
                        String duration = dataobject.optString("duration");
                        listEntity.setDuration(duration);
                        String pic = dataobject.optString("pic");
                        listEntity.setPic(pic);
                        String title = dataobject.optString("title");
                        listEntity.setTitle(title);
                        String typename = dataobject.optString("typename");
                        listEntity.setTypename(typename);
                        int mid = dataobject.optInt("mid");
                        listEntity.setMid(mid);
                        //添加到集合中
                        listBeen.add(listEntity);
                        adapter = new ChaseAdapter(R.layout.item_chase, listBeen);
                        recycle.setAdapter(adapter);
                        recycle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initview() {
        super.initview();

    }


    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public int getlayoutid() {
        return R.layout.fragment_chase;
    }


}
