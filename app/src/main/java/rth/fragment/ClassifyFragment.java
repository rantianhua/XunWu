package rth.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rth.adapter.GridAdapter;
import rth.data.GridItem;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/9.
 */
public class ClassifyFragment extends Fragment {

    @InjectView(R.id.gridview_classify)
    GridView gv;

    private List<GridItem> list;
    private View mainView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initList();
        initView();
    }

    private void initList() {
        list = new ArrayList<>();
        list.add( new GridItem("休闲娱乐",R.drawable.photo1));
        list.add( new GridItem("敏捷游戏",R.drawable.photo2));
        list.add( new GridItem("益智游戏",R.drawable.photo3));
        list.add( new GridItem("角色扮演",R.drawable.photo4));
        list.add( new GridItem("射击游戏",R.drawable.photo5));
        list.add( new GridItem("动作游戏",R.drawable.photo6));
        list.add( new GridItem("体育竞技",R.drawable.photo1));
        list.add( new GridItem("棋牌天地",R.drawable.photo2));
        list.add( new GridItem("冒险游戏",R.drawable.photo3));
        list.add( new GridItem("策略闯关",R.drawable.photo4));
        list.add( new GridItem("儿童教育",R.drawable.photo5));
        list.add( new GridItem("漫画游戏",R.drawable.photo6));
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
        mainView = inflater.inflate(R.layout.fragment_classify,(ViewGroup) getActivity().findViewById(R.id.ll_container),false);
        ButterKnife.inject(this,mainView);
        GridAdapter adapter = new GridAdapter(getActivity(),list);
        gv.setAdapter(adapter);
        inflater = null;
    }


    public static ClassifyFragment getInstance( ) {
        return new ClassifyFragment();
    }
}
