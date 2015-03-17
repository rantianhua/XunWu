package rth.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rth.adapter.ListAdapter;
import rth.data.GameSomeData;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/9.
 */
public class RankFragment extends Fragment implements View.OnClickListener{

    @InjectView(R.id.tv_tab1)
    TextView tvTab1;
    @InjectView(R.id.tv_tab2)
    TextView tvTab2;
    @InjectView(R.id.tv_tab3)
    TextView tvTab3;
    @InjectView(R.id.listview_rank)
    ListView listView;

    private List<GameSomeData> list = null;
    private ListAdapter adapter = null;
    private int originTextColor;
    private Button btnaddMoreGame;
    private ProgressBar pb;
    private View mainView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initData() {
        list = new ArrayList<>();
        for(int i = 0;i < 20;i++) {
            list.add(new GameSomeData());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup p = (ViewGroup) mainView.getParent();
        if(p != null) {
            p.removeAllViewsInLayout();
        }
        return mainView;
    }

    private void initView() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mainView = inflater.inflate(R.layout.fragment_rank,(ViewGroup) getActivity().findViewById(R.id.ll_container),false);
        ButterKnife.inject(this,mainView);
        adapter = new ListAdapter(list,getActivity());
        View footerView = inflater.inflate(R.layout.footer_view,null);
        btnaddMoreGame = (Button) footerView.findViewById(R.id.btn_load_more);
        pb = (ProgressBar ) footerView.findViewById(R.id.pb_load_more);
        pb.setVisibility(View.GONE);
        listView.addFooterView(footerView);
        listView.setAdapter(adapter);
        tvTab1.setOnClickListener(this);
        tvTab2.setOnClickListener(this);
        tvTab3.setOnClickListener(this);
        tvTab1.setText("最热");
        tvTab2.setText("最新");
        tvTab3.setText("推荐");
        tvTab2.setActivated(true);
        tvTab2.setTextColor(getResources().getColor(R.color.white));
        originTextColor = tvTab1.getCurrentTextColor();
        btnaddMoreGame.setOnClickListener(this);
        inflater = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tab1:
                if(!tvTab1.isActivated()) {
                    recoveryTab();
                    tvTab1.setActivated(true);
                    tvTab1.setTextColor(getResources().getColor(R.color.white));
                }
                break;
            case R.id.tv_tab2:
                if(!tvTab2.isActivated()) {
                    recoveryTab();
                    tvTab2.setActivated(true);
                    tvTab2.setTextColor(getResources().getColor(R.color.white));
                }
                break;
            case R.id.tv_tab3:
                if(!tvTab3.isActivated()) {
                    recoveryTab();
                    tvTab3.setActivated(true);
                    tvTab3.setTextColor(getResources().getColor(R.color.white));
                }
                break;
            case R.id.btn_load_more:
                btnaddMoreGame.setEnabled(false);
                pb.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void recoveryTab() {
        if(tvTab1.isActivated()) {
            tvTab1.setActivated(false);
            tvTab1.setTextColor(originTextColor);
            return;
        }
        if(tvTab2.isActivated()) {
            tvTab2.setActivated(false);
            tvTab2.setTextColor(originTextColor);
            return;
        }
        if(tvTab3.isActivated()) {
            tvTab3.setActivated(false);
            tvTab3.setTextColor(originTextColor);
            return;
        }
    }

    public static RankFragment getInstance() {
        return new RankFragment();
    }
}
