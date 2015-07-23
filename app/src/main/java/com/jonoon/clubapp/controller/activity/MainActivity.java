package com.jonoon.clubapp.controller.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.Toast;

import com.jonoon.clubapp.MyApplication;
import com.jonoon.clubapp.R;
import com.jonoon.clubapp.controller.BaseActivityWithJavaScriptInterface;
import com.jonoon.clubapp.controller.MyJavaScriptCallback;
import com.jonoon.clubapp.controller.adapter.ViewPager4SlidingDrawer;
import com.jonoon.clubapp.controller.fragement.main_page.MainPageFragment;
import com.jonoon.clubapp.controller.fragement.main_page.NavigationOneFragment;
import com.jonoon.clubapp.controller.fragement.main_page.NavigationTwoFragment;
import com.jonoon.clubapp.model.constants.ServerUrl;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.view.slidingdrawer.WrappingSlidingDrawer;

public class MainActivity extends BaseActivityWithJavaScriptInterface
        implements NavigationOneFragment.OnFragmentInteractionListener,
        NavigationTwoFragment.OnFragmentInteractionListener{

    private final String TAG = this.getClass().getSimpleName();
    private ViewPager pager;
    private WrappingSlidingDrawer drawer;
    private int cur_page;

    private ImageView mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();

        ((MyApplication)getApplication()).goToIntroduction = false;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    private void initView(){
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new ViewPager4SlidingDrawer(getSupportFragmentManager()));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getIndex(), null)
                )
                .commit();

        drawer = (WrappingSlidingDrawer) findViewById(R.id.drawer);
        mHandler = (ImageView) drawer.findViewById(R.id.handle_icon);

        drawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                mHandler.setImageResource(R.drawable.handler);
            }
        });
        drawer.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener() {
            @Override
            public void onScrollStarted() {
                mHandler.setImageResource(R.drawable.handler_close);
            }

            @Override
            public void onScrollEnded() {
                if(drawer.isOpened()){
                    mHandler.setImageResource(R.drawable.handler);
                }else {
                    mHandler.setImageResource(R.drawable.handler_close);
                }

            }
        });
        drawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {

            @Override
            public void onDrawerClosed() {
                mHandler.setImageResource(R.drawable.handler_close);
            }
        });
    }

    @Override
    protected void onDestroy() {
        L.e(TAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        L.e(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        L.e(TAG,"onResume");
        super.onResume();
        if(((MyApplication)getApplication()).showDrawer){
            drawer.open();
            ((MyApplication)getApplication()).showDrawer = false;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int index = intent.getIntExtra(MyJavaScriptCallback.GO_TO_MODULE, 0);
        onFragmentInteraction(index);
    }

    @Override
    public void onFragmentInteraction(int page_num) {

        if(page_num != cur_page){
            switch (page_num){
                case 1://首页
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getIndex(), null)
                             )
                            .commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case 2://俱乐部
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getClub(), null)
                            )
                            .commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case 3://新闻中心
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getNews(), null)
                            )
                            .commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case 4://赛事
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getMatch(), null)
                            )
                            .commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case 5://校园足球
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getCampusFootball(), null)
                            )
                            .commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case 6://足球公园
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getFootballPark(), null)
                            )
                            .commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case 7://微社区
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getBBS(), null)
                            )
                            .commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case 8://微商城
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getMall(), null)
                            )
                            .commit();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;

                case 9://微票务
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getTicket(), null)
                            )
                            .commit();

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;

                case 10://品牌文化
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getCulture(), null)
                            )
                            .commit();

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    break;
                case 11://VIP
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance(ServerUrl.getVip(), null)
                            )
                            .commit();

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }
        cur_page = page_num;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){

            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(params);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            if(drawer.isOpened() && !drawer.isMoving()){
                drawer.close();
            }
        }else {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(params);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }
        super.onConfigurationChanged(newConfig);
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(drawer.isOpened() && !drawer.isMoving()){
                drawer.animateClose();
                return true;
            }else {
                Fragment fg = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (fg instanceof MainPageFragment) {
                    boolean mCanGoBackgoback = ((MainPageFragment)fg).canGoBack();
                    if (mCanGoBackgoback){
                        ((MainPageFragment)fg).goBack();
                        return true;
                    }
                }
            }
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(this,"再按一次返回桌面",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

}
