package rth.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rht.util.Utils;
import rth.CustomView.RoundedImageView;
import rth.adapter.CycleAdapter;
import rth.xunwu.GameDetailActivity;
import rth.xunwu.R;

/**
 * Created by Rth on 2015/3/9.
 */
public class HomeFragment extends Fragment {

    @InjectView(R.id.vp_cycle)
    ViewPager vp;
    @InjectView(R.id.horizal_guess_you_like)
    HorizontalScrollView hsGuessYouLike;
    @InjectView(R.id.rl_hori_guess)
    RelativeLayout rlHoriGuess;
    @InjectView(R.id.rl_hori_recommend)
    RelativeLayout rlHoriRecommend;
    @InjectView(R.id.rl_hori_recommend2)
    RelativeLayout rlHoriRecommend2;
    @InjectView(R.id.fl_hori_block1)
    FrameLayout flHoriBlock1;
    @InjectView(R.id.fl_hori_block2)
    FrameLayout flHoriBlock2;
    @InjectView(R.id.ll_fine_game)
    LinearLayout llFineGame;

    private List<Integer> list;
    private Timer timer;
    private Handler timerHandler;
    private float dentisy ;
    private Resources res;
    public String tag;
    private View mainView = null;
    private LayoutInflater inflater = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getResources();
        dentisy = res.getDisplayMetrics().density;
        initData();
        initHandler();
        initView();
    }

    private void initHandler() {
        timerHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                vp.setCurrentItem(msg.what);
            }
        };
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(R.drawable.photo1);
        list.add(R.drawable.photo2);
        list.add(R.drawable.photo3);
        list.add(R.drawable.photo4);
        list.add(R.drawable.photo5);
        list.add(R.drawable.photo6);
        list.add(R.drawable.photo7);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //为了避免在重新显示该View时重新绘制，于是将View作为一个全局变量保存状态，并放在onCreate中创建，保证在pop的时候不会重新实例化
        ViewGroup p = (ViewGroup)mainView.getParent();
        if(p != null) {
            p.removeAllViewsInLayout();
        }
        return mainView;
    }

    private void initView() {
        inflater = getActivity().getLayoutInflater();
        mainView = inflater.inflate(R.layout.fragment_home,(ViewGroup)getActivity().findViewById(R.id.ll_container),false);
        ButterKnife.inject(this, mainView);
        CycleAdapter cycleAdapter = new CycleAdapter(getActivity(),list);
        vp.setAdapter(cycleAdapter);
        addHorizentalViews();
        inflater = null;
    }

    private void addHorizentalViews() {
        //添加“猜你喜欢”模块的视图
        int leftMargin = res.getDimensionPixelSize(R.dimen.hori_child_margin);
        int horiChildSize =  res.getDimensionPixelSize(R.dimen.hori_child_size);
        for(int i = 0;i < 5;i++) {
            View v = inflater.inflate(R.layout.hori_child,null);
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
            v.setLayoutParams(params);
            params = null;
            Utils.loadBitmapFromResource(getActivity().getResources(), iv, R.drawable.game, 60, 60, -1);
            rlHoriGuess.addView(v);
        }

        //添加“小编推荐”模块的视图
        for(int i = 0;i < 5;i++) {
            View v = inflater.inflate(R.layout.hori_child,null);
            RoundedImageView iv = (RoundedImageView) v.findViewById(R.id.round_img_hori_child);
            Utils.loadBitmapFromResource(getActivity().getResources(), iv, R.drawable.game, 60, 60, -1);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(horiChildSize , horiChildSize);
            if (i == 0) {
                params.setMargins(leftMargin / 2, 0, 0, 0);
            }else if(i == 4) {
                params.setMargins(horiChildSize * 4 + leftMargin, 0, leftMargin, 0);
            }else {
                params.setMargins(horiChildSize * i + leftMargin, 0, 0, 0);
            }
            v.setLayoutParams(params);
            params = null;
            rlHoriRecommend.addView(v);
        }

        for(int i = 0;i < 5;i++) {
            View v = inflater.inflate(R.layout.hori_child,null);
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
            v.setLayoutParams(params);
            params = null;
            Utils.loadBitmapFromResource(getActivity().getResources(), iv, R.drawable.game, 60, 60, -1);
            rlHoriRecommend2.addView(v);
        }

        int blockWidth = Utils.getScreenWidth(getActivity()) /  2;
        //添加板块一的视图
        for(int i = 0;i < 3;i++) {
            RoundedImageView iv = new RoundedImageView(getActivity());
            //设置RoundedImageView的宽高
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    blockWidth, 110);
            params.setMargins(30 * (i+1)  + blockWidth * i, 15, 30, 15);
            iv.setLayoutParams(params);
            iv.setOval(false);
            iv.setCornerRadius(20);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Utils.loadBitmapFromResource(getActivity().getResources(),iv,R.drawable.photo3,params.width
                    ,params.height,-1);
            flHoriBlock1.addView(iv);
        }

        //添加板块二的视图
        for(int i = 0;i < 4;i++) {
            RoundedImageView iv = new RoundedImageView(getActivity());
            //设置RoundedImageView的宽高
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                   blockWidth,110);
            params.setMargins(30 * (i+1)  + blockWidth * i, 15, 30, 15);
            iv.setLayoutParams(params);
            iv.setOval(false);
            iv.setCornerRadius(20);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Utils.loadBitmapFromResource(getActivity().getResources(), iv, R.drawable.photo4, params.width
                    , params.height, -1);
            flHoriBlock2.addView(iv);
        }

        //添加”精品游戏”的视图
        for(int i = 0;i < 5;i++) {
            View v = inflater.inflate(R.layout.list_item,null);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), GameDetailActivity.class);
                    startActivity(i);
                }
            });
            llFineGame.addView(v);
            if(i < 4) {
                View line = new View(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.height = 1;
                params.setMargins(20,0,20,0);
                line.setLayoutParams(params);
                params = null;
                line.setBackgroundColor(getActivity().getResources().getColor(R.color.grey_light));
                llFineGame.addView(line);
            }
        }
    }

    private void initTimer() {
        timer = new Timer();
        //定义计划任务
        timer.schedule(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                Message m = timerHandler.obtainMessage();
                m.what = i++;
                timerHandler.sendMessage(m);
                if(i == list.size()-1) {
                    i = 0;
                }
            }
        },0,2000);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(timer == null) {
            initTimer();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }
}
