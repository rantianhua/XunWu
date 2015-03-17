package rth.xunwu;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rht.util.Utils;
import rth.fragment.HowToPlayFragment;
import rth.fragment.IntroductionFragment;

/**
 * Created by Rth on 2015/3/12.
 */
public class GameDetailActivity extends FragmentActivity implements View.OnClickListener{

    @InjectView(R.id.img_dot1)
    ImageView dot1;
    @InjectView(R.id.img_dot2)
    ImageView dot2;
    @InjectView(R.id.vp_detail)
    ViewPager vpPictures;
    @InjectView(R.id.tv_collection_detail)
    TextView tvCollection;
    @InjectView(R.id.tv_start_game)
    TextView tvStartGame;
    @InjectView(R.id.tv_like)
    TextView tvLike;
    @InjectView(R.id.tv_tab1)
    TextView tvTab1;
    @InjectView(R.id.tv_tab2)
    TextView tvTab2;
    @InjectView(R.id.tv_tab3)
    TextView tvTab3;

    private Resources res = null;
    private int activedColor,unactivedColor;
    private FragmentManager fm = getSupportFragmentManager();
    private FragmentTransaction transaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        res = getResources();
        activedColor = res.getColor(R.color.white);
        unactivedColor = res.getColor(android.R.color.secondary_text_light);
        initView();
    }

    private void initView() {
        ButterKnife.inject(this);
        //显示游戏截图
        int height = (int) (res.getDimensionPixelSize(R.dimen.game_picture_height)
                + res.getDimensionPixelSize(R.dimen.activity_vertical_margin));
        ViewGroup.LayoutParams params = vpPictures.getLayoutParams();
        params.height = height;
        vpPictures.setLayoutParams(params);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v1 = inflater.inflate(R.layout.introduction_pictures,null);
        final ImageView  img1 =(ImageView) v1.findViewById(R.id.img1);
        final ImageView  img2 =(ImageView) v1.findViewById(R.id.img2);
        View v2 = inflater.inflate(R.layout.introduction_pictures,null);
        final ImageView  img3 =(ImageView) v2.findViewById(R.id.img1);
        final ImageView  img4 =(ImageView) v2.findViewById(R.id.img2);
        ViewTreeObserver vto2 = img1.getViewTreeObserver();
        final int id1 = R.drawable.one;
        final int id2 = R.drawable.two;
        final int id3 = R.drawable.three;
        final int id4 = R.drawable.four;
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    img1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }else {
                    img1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                int w = img1.getWidth();
                int h = img1.getHeight();
                Utils.loadBitmapFromResource(res, img1, id1, w, h, -1);
                Utils.loadBitmapFromResource(res, img2, id2, w, h, -1);
                Utils.loadBitmapFromResource(res, img3, id3, w, h, -1);
                Utils.loadBitmapFromResource(res, img4, id4, w, h, -1);
            }
        });
        vto2 = null;

        final List<View> listViews = new ArrayList<>();
        listViews.add(v1);
        listViews.add(v2);
        vpPictures.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return listViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View v= listViews.get(position);
                container.addView(v);
                return v;
            }
        });
        vpPictures.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    dot1.setImageResource(R.drawable.dot_red);
                    dot2.setImageResource(R.drawable.dot_grey);
                }else {
                    dot2.setImageResource(R.drawable.dot_red);
                    dot1.setImageResource(R.drawable.dot_grey);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tvTab1.setText("介绍");
        tvTab2.setText("玩法");
        tvTab3.setText("排行");
        tvTab1.setActivated(true);
        tvTab1.setTextColor(activedColor);

        tvCollection.setOnClickListener(this);
        tvLike.setOnClickListener(this);
        tvStartGame.setOnClickListener(this);
        tvTab1.setOnClickListener(this);
        tvTab2.setOnClickListener(this);
        tvTab3.setOnClickListener(this);

        changeFragment("introduction");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_collection_detail:
                Drawable drawable = res.getDrawable(R.drawable.correct);
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                tvCollection.setCompoundDrawables(drawable,null,null,null);
                tvCollection.setCompoundDrawablePadding(res.getDimensionPixelSize(R.dimen.drawable_padding));
                tvCollection.setText("已收藏");
                tvCollection.setClickable(false);
                break;
            case R.id.tv_start_game:
                break;
            case R.id.tv_like:
                break;
            case R.id.tv_tab1:
                if(!tvTab1.isActivated()){
                    unActiveOthers();
                    tvTab1.setActivated(true);
                    tvTab1.setTextColor(activedColor);
                    changeFragment("introduction");
                }
                break;
            case R.id.tv_tab2:
                if(!tvTab2.isActivated()){
                    unActiveOthers();
                    tvTab2.setActivated(true);
                    tvTab2.setTextColor(activedColor);
                    changeFragment("howplay");
                }
                break;
            case R.id.tv_tab3:
                if(!tvTab3.isActivated()){
                    unActiveOthers();
                    tvTab3.setActivated(true);
                    tvTab3.setTextColor(activedColor);
                }
                break;
             default:
                 break;
        }
    }

    private void changeFragment(String tag) {
        if(transaction == null) {
            transaction = fm.beginTransaction();
        }
        Fragment fragment = fm.findFragmentByTag(tag);
        if(tag.equals("introduction")) {
           if(fragment == null) {
               fragment = IntroductionFragment.getInstance();
               transaction.add(R.id.rl_detail_sub_container,fragment,tag);
           }else {
               transaction.attach(fragment);
               //设置另一个Fragment不可见
               fm.findFragmentByTag("howplay").setMenuVisibility(false);
               fm.findFragmentByTag("howplay").setUserVisibleHint(false);
           }
        }else {
            if(fragment == null) {
                fragment = new HowToPlayFragment();
                transaction.add(R.id.rl_detail_sub_container,fragment,tag);
            }else {
                transaction.attach(fragment);
            }
            fm.findFragmentByTag("introduction").setMenuVisibility(false);
            fm.findFragmentByTag("introduction").setUserVisibleHint(false);
        }
        fragment.setMenuVisibility(true);
        fragment.setUserVisibleHint(true);
        finishTranscation();
    }

    //将actived的TextView的状态改为unactived
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
        }
    }

    private void finishTranscation(){
        if(transaction != null) {
           transaction.commitAllowingStateLoss();
           fm.executePendingTransactions();
            transaction = null;
        }
    }
}
