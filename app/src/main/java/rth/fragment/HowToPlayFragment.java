package rth.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/15.
 */
public class HowToPlayFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_how_to_play,container,false);
        initView(v);
        ViewGroup p = (ViewGroup) v.getParent();
        if(p != null) {
            p.removeAllViewsInLayout();
        }
        return v;
    }

    private void initView(View v) {
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}
