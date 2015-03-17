package rth.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rth.fragment.footprint_fragments.MyFootprintFragment;
import rth.fragment.footprint_fragments.NoFootprintFragment;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/9.
 */
public class FootprintFragment extends Fragment implements View.OnClickListener{

    @InjectView(R.id.ll_footprint_note)
    LinearLayout llNote;
    @InjectView(R.id.tv_footprint_login)
    TextView tvLogin;
    @InjectView(R.id.tv_tab1)
    TextView tvTab1;
    @InjectView(R.id.tv_tab2)
    TextView tvTab2;
    @InjectView(R.id.tv_tab3)
    TextView tvTab3;

    private FragmentManager fm = null;
    private int activedColor;
    private int unactivedColor;
    private View mainView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getActivity().getResources();
        activedColor = res.getColor(R.color.white);
        unactivedColor = res.getColor(android.R.color.secondary_text_light);
        res = null;
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           ViewGroup  p = (ViewGroup) mainView.getParent();
        if(p != null) {
            p.removeAllViewsInLayout();
        }
        return mainView;
    }

    private void initView() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mainView = inflater.inflate(R.layout.fragment_footprint,(ViewGroup) getActivity().findViewById(R.id.ll_container),false);
        ButterKnife.inject(this,mainView);
        tvTab1.setText("收藏夹");
        tvTab2.setText("我玩过的");
        tvTab3.setText("我喜欢的");
        tvTab1.setOnClickListener(this);
        tvTab2.setOnClickListener(this);
        tvTab3.setOnClickListener(this);
        tvTab2.setActivated(true);
        tvTab2.setTextColor(activedColor);
        fm = getChildFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.ll_footprint_container);
        if(fragment == null) {
            fm.beginTransaction().add(R.id.ll_footprint_container,new MyFootprintFragment()).commit();
        }
        inflater = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tab1:
                if(!tvTab1.isActivated()) {
                    fm.beginTransaction().replace(R.id.ll_footprint_container,new NoFootprintFragment()).commit();
                    unActiveOthers();
                    tvTab1.setActivated(true);
                    tvTab1.setTextColor(activedColor);
                    showNote();
                }
                break;
            case R.id.tv_tab2:
                if(!tvTab2.isActivated()) {
                    fm.beginTransaction().replace(R.id.ll_footprint_container,new MyFootprintFragment()).commit();
                    unActiveOthers();
                    tvTab2.setActivated(true);
                    tvTab2.setTextColor(activedColor);
                    hideNote();
                }
                break;
            case R.id.tv_tab3:
                if(!tvTab3.isActivated()) {
                    fm.beginTransaction().replace(R.id.ll_footprint_container,new NoFootprintFragment()).commit();
                    unActiveOthers();
                    tvTab3.setActivated(true);
                    tvTab3.setTextColor(activedColor);
                    hideNote();
                }
                break;
            case R.id.tv_footprint_login:
                break;
        }
    }

    private void unActiveOthers() {
        if(tvTab1.isActivated()) {
            tvTab1.setActivated(false);
            tvTab1.setTextColor(unactivedColor);
            return;
        }
        if(tvTab2.isActivated()) {
            tvTab2.setActivated(false);
            tvTab2.setTextColor(unactivedColor);
            return;
        }
        if(tvTab3.isActivated()) {
            tvTab3.setActivated(false);
            tvTab3.setTextColor(unactivedColor);
            return;
        }
    }

    private void hideNote() {
        if(llNote.getVisibility() == View.VISIBLE) {
            llNote.setVisibility(View.GONE);
        }
    }

    private void showNote() {
        if(llNote.getVisibility() == View.GONE) {
            llNote.setVisibility(View.VISIBLE);
        }
    }

    public static FootprintFragment getInstance(){
        return new FootprintFragment();
    }
}
