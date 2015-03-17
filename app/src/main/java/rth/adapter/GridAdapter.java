package rth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import rht.util.Utils;
import rth.CustomView.RoundedImageView;
import rth.data.GridItem;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/11.
 */
public class GridAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<GridItem> list;
    private Context c;

    public GridAdapter(Context context,List<GridItem> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.c = context;
    }

    @Override
    public int getCount()
    {
          return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.grid_item, null);
            viewHolder = new ViewHolder();
            viewHolder.classify = (TextView) convertView.findViewById(R.id.tv_grid_classify);
            viewHolder.image = (RoundedImageView) convertView.findViewById(R.id.rounded_img_grid);
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.classify.setText(list.get(position).getClassify());
        Utils.loadBitmapFromResource(c.getResources(),viewHolder.image,list.get(position).getImageId(),160,80,-1);
        //viewHolder.image.setImageResource(list.get(position).getImageId());
        return convertView;
    }

    static class ViewHolder
    {
        TextView classify;
        RoundedImageView image;
    }

}
