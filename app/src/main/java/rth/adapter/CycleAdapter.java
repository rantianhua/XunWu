package rth.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import rht.util.Utils;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/9.
 */
public class CycleAdapter extends PagerAdapter {

    private List<View> views;   //存放视图
    private List<Integer>list;
    private Context context;
    private static int w = 0,h=0;

    public CycleAdapter(Context context,List<Integer> list) {
        this.context = context;
        this.list = list;
        views = new ArrayList<>();
        initViews();
    }

    @TargetApi(16)
    private void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        for (int i = 0;i < list.size();i++) {
            View v = layoutInflater.inflate(R.layout.view_circle,null);
            final ImageView img = (ImageView) v.findViewById(R.id.img_circle);
            //得到img的宽高
            final int id = list.get(i);
            ViewTreeObserver vto2 = img.getViewTreeObserver();
            vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        img.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        Utils.loadBitmapFromResource(context.getResources(), img, id, img.getWidth(), img.getHeight(), -1);
                    }
                });
            views.add(v);
            }
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v= views.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
