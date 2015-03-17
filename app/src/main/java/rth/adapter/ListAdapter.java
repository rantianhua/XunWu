package rth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import rth.CustomView.RoundedImageView;
import rth.data.GameSomeData;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/10.
 */
public class ListAdapter extends BaseAdapter {

    private List<GameSomeData> list = null;
    private LayoutInflater inflater = null;
    public ListAdapter(List<GameSomeData> list,Context context) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler = null;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item,null);
            hodler = new ViewHodler();
            convertView.setTag(hodler);
        }else {
            hodler = (ViewHodler) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHodler {
        RoundedImageView iv;
        TextView tvGameName,tvRq,tvGameDescription,tvStart;
        RatingBar rb;
    }
}
