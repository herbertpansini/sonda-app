package com.androidtutz.anushka.tmdbclient.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidtutz.anushka.tmdbclient.R;
import com.androidtutz.anushka.tmdbclient.model.Wiki;

import java.util.List;

public class HomeAdapter extends BaseAdapter {

    public List<Wiki> list;
    Activity activity;

    public HomeAdapter(Activity activity, List<Wiki> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView tvLabel;
        TextView tvValue;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.content_home, null);
            holder = new ViewHolder();
            holder.tvLabel = (TextView) convertView.findViewById(R.id.tvLabel);
            holder.tvValue = (TextView) convertView.findViewById(R.id.tvValue);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Wiki wiki = list.get(position);
        holder.tvLabel.setText(wiki.getLabel());
        holder.tvValue.setText(wiki.getValue());

        return convertView;
    }
}