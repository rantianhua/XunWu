package rth.fragment.footprint_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rth.adapter.ListAdapter;
import rth.data.GameSomeData;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/11.
 */
public class MyFootprintFragment extends Fragment {

    @InjectView(R.id.listview_myfootprint)
    ListView listView;

    private List<GameSomeData> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        for(int i = 0;i < 10;i++) {
            list.add(new GameSomeData());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myfootprint,container,false);
        initView(v);
        return  v;
    }

    private void initView(View v) {
        ButterKnife.inject(this,v);
        ListAdapter adapter = new ListAdapter(list,getActivity());
        listView.setAdapter(adapter);
    }
}
