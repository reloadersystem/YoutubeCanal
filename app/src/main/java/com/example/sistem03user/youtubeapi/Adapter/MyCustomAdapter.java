package com.example.sistem03user.youtubeapi.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.sistem03user.youtubeapi.Model.VideoDetails;

import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<VideoDetails> videoDetailsArrayList;



    public MyCustomAdapter(Activity activity, ArrayList<VideoDetails> videoDetailsArrayList) {

        this.activity= activity;
        this.videoDetailsArrayList=videoDetailsArrayList;

    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public  long getItemId(int position)
    {
        return 0;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }

    @Override
    public  int getCount()
    {
        return 0;
    }

}
