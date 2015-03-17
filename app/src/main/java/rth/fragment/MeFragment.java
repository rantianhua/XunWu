package rth.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rth.xunwu.LoginActivity;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/9.
 */
public class MeFragment extends Fragment  implements View.OnClickListener{

    @InjectView(R.id.tv_login_rightnow)
    TextView tvLogin;

    private View mainView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       ViewGroup p =(ViewGroup) mainView.getParent();
        if(p != null){
            p.removeAllViewsInLayout();
        }
        return mainView;
    }

    private void initView() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mainView = inflater.inflate(R.layout.fragment_me,(ViewGroup) getActivity().findViewById(R.id.ll_container),false);
        ButterKnife.inject(this,mainView);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tv_login_rightnow:
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                break;
        }
    }

    public static MeFragment getInstance() {
        return new MeFragment();
    }
}
