package rth.xunwu;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rht.util.Constans;
import rht.util.Utils;
import rth.fragment.ClassifyFragment;
import rth.fragment.FootprintFragment;
import rth.fragment.HomeFragment;
import rth.fragment.MeFragment;
import rth.fragment.RankFragment;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @InjectView(R.id.ll_home)
    LinearLayout llHome;
    @InjectView(R.id.ll_rank)
    LinearLayout llRank;
    @InjectView(R.id.ll_classify)
    LinearLayout llClassify;
    @InjectView(R.id.ll_footprint)
    LinearLayout llFootprint;
    @InjectView(R.id.ll_me)
    LinearLayout llMe;
    @InjectView(R.id.img_home)
    ImageView imgHome;
    @InjectView(R.id.img_rank)
    ImageView imgRank;
    @InjectView(R.id.img_classify)
    ImageView imgClassify;
    @InjectView(R.id.img_footprint)
    ImageView imgFootprint;
    @InjectView(R.id.img_me)
    ImageView imgMe;
    @InjectView(R.id.tv_home)
    TextView tvHome;
    @InjectView(R.id.tv_rank)
    TextView tvRank;
    @InjectView(R.id.tv_classify)
    TextView tvClassify;
    @InjectView(R.id.tv_footprint)
    TextView tvFootprint;
    @InjectView(R.id.tv_me)
    TextView tvMe;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_search)
    ImageButton ibSearch;
    @InjectView(R.id.ll_container)
    LinearLayout llContainer;

    private Resources res = null;
    private int unSelectedColor,selectedColor;
    private int selectId = -1;
    private Fragment home = HomeFragment.getInstance();
    private Fragment rank = RankFragment.getInstance();
    private Fragment classify = ClassifyFragment.getInstance();
    private Fragment footprint = FootprintFragment.getInstance();
    private Fragment me = MeFragment.getInstance();
    private FragmentManager fm = getSupportFragmentManager();
    private FragmentTransaction transaction = null;
    private int navImgSzie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();
        unSelectedColor = res.getColor(android.R.color.secondary_text_light);
        selectedColor = res.getColor(R.color.red);
        navImgSzie = res.getDimensionPixelSize(R.dimen.nav_img_size);
        initView();
    }

    private void initView() {
        ButterKnife.inject(this);
        tvTitle.setText(Constans.TITLEHOME);
        tvHome.setTextColor(selectedColor);
        selectId = llHome.getId();
        Utils.loadBitmapFromResource(res, imgHome, R.drawable.home, navImgSzie ,navImgSzie , R.color.red);
        Utils.loadBitmapFromResource(res,imgRank,R.drawable.rank,navImgSzie,navImgSzie,android.R.color.tertiary_text_light);
        Utils.loadBitmapFromResource(res,imgClassify,R.drawable.classify,navImgSzie,navImgSzie,android.R.color.tertiary_text_light);
        Utils.loadBitmapFromResource(res,imgFootprint,R.drawable.footprint,navImgSzie,navImgSzie,android.R.color.tertiary_text_light);
        Utils.loadBitmapFromResource(res,imgMe,R.drawable.me,navImgSzie,navImgSzie,android.R.color.tertiary_text_light);
        llHome.setOnClickListener(this);
        llMe.setOnClickListener(this);
        llFootprint.setOnClickListener(this);
        llRank.setOnClickListener(this);
        llClassify.setOnClickListener(this);
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams) llContainer.getLayoutParams();
        params.setMargins(0,res.getDimensionPixelSize(R.dimen.top_height)+3,0,res.getDimensionPixelSize(R.dimen.bottom_height));
        llContainer.setLayoutParams(params);
        params =null;
        updateFragment(0);
    }

    private void updateFragment(int position) {
        if(transaction == null) {
            transaction = fm.beginTransaction();
        }
        switch (position) {
            case 0:
                transaction.replace(R.id.ll_container, home);
                while (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStackImmediate();
                }
                break;
            case 1:
                transaction.replace(R.id.ll_container, rank) .addToBackStack(null);
                break;
            case 2:
                transaction.replace(R.id.ll_container, classify) .addToBackStack(null);
                break;
            case 3:
                transaction.replace(R.id.ll_container, footprint) .addToBackStack(null);
                break;
            case 4:
                transaction.replace(R.id.ll_container, me) .addToBackStack(null);
        }
        finishTranscation();
    }

    private void finishTranscation() {
        if(transaction != null) {
            transaction.commitAllowingStateLoss();
            transaction = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_me:
                if(selectId != v.getId()) {
                    selectItem(imgMe,tvMe,R.drawable.me,navImgSzie,navImgSzie
                            ,R.id.ll_me,Constans.TITLEME,4);
                }
                break;
            case R.id.ll_home:
                if (selectId != v.getId()) {
                    selectItem(imgHome, tvHome, R.drawable.home, navImgSzie, navImgSzie
                            , R.id.ll_home, Constans.TITLEHOME, 0);
                }
                break;
            case R.id.ll_rank:
                if (selectId != v.getId()) {
                    selectItem(imgRank, tvRank, R.drawable.rank, navImgSzie, navImgSzie
                            , R.id.ll_rank, Constans.TITLERANK, 1);
                }
                break;
            case R.id.ll_classify:
                if (selectId != v.getId()) {
                    selectItem(imgClassify, tvClassify, R.drawable.classify, navImgSzie, navImgSzie
                            , R.id.ll_classify, Constans.TITLECLASSIFY, 2);
                }
                break;
            case R.id.ll_footprint:
                if (selectId != v.getId()) {
                    selectItem(imgFootprint, tvFootprint, R.drawable.footprint, navImgSzie, navImgSzie
                            , R.id.ll_footprint, Constans.TITLEFOOTPRINT, 3);
                }
                break;
            case R.id.tv_back:
               goToBack();
                break;
            default:
                break;
        }
    }

    //设置选中部分为红色，并更新title和fragment
    private void selectItem(ImageView img, TextView tv, int drawableId, int drawerWidth, int drawerHeight,int clickId, String title,int position) {
        recoveryItem();
        changeBottom(img,tv,drawableId,drawerWidth,drawerHeight,R.color.red,selectedColor);
        selectId = clickId;
        tvTitle.setText(title);
        updateFragment(position);
        if (position == 0) {
            hideBack();
        }else {
            showBack();
        }
    }

    //恢复位选中部分为暗色
    private void recoveryItem() {
        if(selectId == -1) {return;}
        switch (selectId) {
            case R.id.ll_home:
                changeBottom(imgHome, tvHome, R.drawable.home, 20, 20, android.R.color.tertiary_text_light,unSelectedColor);
                break;
            case R.id.ll_classify:
                changeBottom(imgClassify, tvClassify, R.drawable.classify, 20, 20, android.R.color.tertiary_text_light,unSelectedColor);
                break;
            case R.id.ll_rank:
                changeBottom(imgRank, tvRank, R.drawable.rank, 20, 20, android.R.color.tertiary_text_light,unSelectedColor);
                break;
            case R.id.ll_footprint:
                changeBottom(imgFootprint, tvFootprint, R.drawable.footprint, 20, 20, android.R.color.tertiary_text_light,unSelectedColor);
                break;
            case R.id.ll_me:
                changeBottom(imgMe, tvMe, R.drawable.me, 20, 20, android.R.color.tertiary_text_light,unSelectedColor);
                break;
            default:
                break;
        }
    }

    //改变底部图片以及文字的颜色
    private void changeBottom(ImageView v, TextView tv, int imgId, int w, int h, int imgColorId,int tvColor) {
        Utils.loadBitmapFromResource(res,v,imgId,w,h,imgColorId);
        tv.setTextColor(tvColor);
    }

    //显示头部"返回"TextView
    private void showBack() {
        if(tvBack.getVisibility() == View.GONE) {
            tvBack.setVisibility(View.VISIBLE);
            tvBack.setOnClickListener(this);
            tvBack.setOnClickListener(this);
        }
    }

    //隐藏头部"返回"TextView
    private void hideBack() {
        if(tvBack.getVisibility() == View.VISIBLE) {
            tvBack.setVisibility(View.GONE);
            tvBack.setOnClickListener(null);
        }
    }

    //监听返回事件
        @Override
        public void onBackPressed() {
            if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
                goToBack();
            }else {
                super.onBackPressed();
            }
    }

    private void goToBack() {
        fm.popBackStackImmediate();
        recoveryItem();
        if(rank.isVisible()) {
            changeBottom(imgRank,tvRank,R.drawable.rank,navImgSzie,navImgSzie,R.color.red,selectedColor);
            selectId = R.id.ll_rank;
            tvTitle.setText(Constans.TITLERANK);
            showBack();
        }else if(home.isVisible()) {
            changeBottom(imgHome,tvHome,R.drawable.home,navImgSzie,navImgSzie,R.color.red,selectedColor);
            selectId = R.id.ll_home;
            tvTitle.setText(Constans.TITLEHOME);
            hideBack();
        }else if(classify.isVisible()) {
            changeBottom(imgClassify,tvClassify,R.drawable.classify,navImgSzie,navImgSzie,R.color.red,selectedColor);
            selectId = R.id.ll_classify;
            showBack();
            tvTitle.setText(Constans.TITLECLASSIFY);
        }else if(footprint.isVisible()) {
            changeBottom(imgFootprint,tvFootprint,R.drawable.footprint,navImgSzie,navImgSzie,R.color.red,selectedColor);
            selectId = R.id.ll_footprint;
            showBack();
            tvTitle.setText(Constans.TITLEFOOTPRINT);
        }else if(me.isVisible()){
            changeBottom(imgMe,tvMe,R.drawable.me,navImgSzie,navImgSzie,R.color.red,selectedColor);
            selectId = R.id.ll_me;
            showBack();
            tvTitle.setText(Constans.TITLEME);
        }
    }
}
