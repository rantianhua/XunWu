package rth.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rht.util.Utils;
import rth.CustomView.RoundedImageView;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/12.
 */
public class IntroductionFragment extends Fragment {

    @InjectView(R.id.rl_description_share_game_hori)
    RelativeLayout rlRelativeGame;
    @InjectView(R.id.ll_remark)
    LinearLayout llRemark;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_description,container,false);
        initView(v);
        ViewGroup p = (ViewGroup) v.getParent();
        if(p != null) {
            p.removeAllViewsInLayout();
        }
       return v;
    }

    private void initView(View v) {
        ButterKnife.inject(this,v);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        //相关游戏
        int leftMargin = getResources().getDimensionPixelSize(R.dimen.hori_child_margin);
        int horiChildSize =  getResources().getDimensionPixelSize(R.dimen.hori_child_size);
        for(int i = 0;i < 5;i++) {
            View view = inflater.inflate(R.layout.hori_child,null);
            RoundedImageView iv = (RoundedImageView) v.findViewById(R.id.round_img_hori_child);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(horiChildSize
                    , horiChildSize);
            if (i == 0) {
                params.setMargins(leftMargin / 2, 0, 0, 0);
            }else if(i == 4) {
                params.setMargins(horiChildSize * 4 + leftMargin, 0, leftMargin, 0);
            }else {
                params.setMargins(horiChildSize * i + leftMargin, 0, 0, 0);
            }
            view.setLayoutParams(params);
            params = null;
            Utils.loadBitmapFromResource(getActivity().getResources(), iv, R.drawable.game, 60, 60, -1);
            rlRelativeGame.addView(view);
          }


        //添加评论
        for (int i =0;i < 5; i++) {
            View markView = inflater.inflate(R.layout.remark_item,null);
            if(i == 2) {
                RelativeLayout rl = (RelativeLayout) markView.findViewById(R.id.rl_remark_picture);
                ImageView img = new ImageView(getActivity());
                Utils.loadBitmapFromResource(getResources(),img,R.drawable.game,50,50,-1);
                rl.addView(img);
            }
            llRemark.addView(markView);
            if(i < 4) {
                View line = new View(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.height = 1;
                params.setMargins(20,0,20,0);
                line.setLayoutParams(params);
                params = null;
                line.setBackgroundColor(getActivity().getResources().getColor(R.color.grey_light));
                llRemark.addView(line);
            }
        }

    }

    public static IntroductionFragment getInstance() {
        return new IntroductionFragment();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }

}
