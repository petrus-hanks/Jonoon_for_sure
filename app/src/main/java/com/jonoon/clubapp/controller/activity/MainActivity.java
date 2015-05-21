package com.jonoon.clubapp.controller.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SlidingDrawer;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jonoon.clubapp.MyApplication;
import com.jonoon.clubapp.R;
import com.jonoon.clubapp.controller.adapter.ViewPager4SlidingDrawer;
import com.jonoon.clubapp.controller.fragement.main_page.FixtureFragment;
import com.jonoon.clubapp.controller.fragement.main_page.MainPageFragment;
import com.jonoon.clubapp.controller.fragement.main_page.NavigationOneFragment;
import com.jonoon.clubapp.controller.fragement.main_page.NavigationTwoFragment;
import com.jonoon.clubapp.controller.fragement.main_page.NewsFragment;
import com.jonoon.clubapp.controller.fragement.main_page.SquadFragment;
import com.jonoon.clubapp.model.bean.Fixture;
import com.jonoon.clubapp.model.bean.FixtureItem;
import com.jonoon.clubapp.util.DateHelper;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.util.net.GsonRequest;
import com.jonoon.clubapp.util.net.VolleyHelper;
import com.jonoon.clubapp.view.custom_view.WaitingDialog;
import com.jonoon.clubapp.view.slidingdrawer.WrappingSlidingDrawer;

public class MainActivity extends FragmentActivity
        implements NavigationOneFragment.OnFragmentInteractionListener,
        NavigationTwoFragment.OnFragmentInteractionListener{

    private final String TAG = this.getClass().getSimpleName();
    private ViewPager pager;
    private WrappingSlidingDrawer drawer;
    private int cur_page;

    private ImageView mHandler;
    private ImageView mArrowDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();

        ((MyApplication)getApplication()).goToIntroduction = false;
    }


    private void initView(){
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new ViewPager4SlidingDrawer(getSupportFragmentManager()));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, NewsFragment.newInstance("http://www.baidu.com", null)
                )
                .commit();

        drawer = (WrappingSlidingDrawer) findViewById(R.id.drawer);
        mHandler = (ImageView) drawer.findViewById(R.id.handle_icon);

        mArrowDown = (ImageView) drawer.findViewById(R.id.arrow_down);

        drawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                mHandler.setImageResource(R.drawable.handler);
                mArrowDown.setVisibility(View.VISIBLE);
            }
        });
        drawer.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener() {
            @Override
            public void onScrollStarted() {
                mHandler.setImageResource(R.drawable.handler_close);
                mArrowDown.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onScrollEnded() {
                if(drawer.isOpened()){
                    mHandler.setImageResource(R.drawable.handler);
                    mArrowDown.setVisibility(View.VISIBLE);
                }else {
                    mHandler.setImageResource(R.drawable.handler_close);
                    mArrowDown.setVisibility(View.INVISIBLE);
                }

            }
        });
        drawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {

            @Override
            public void onDrawerClosed() {
                mHandler.setImageResource(R.drawable.handler_close);
                mArrowDown.setVisibility(View.INVISIBLE);
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
    }

    @Override
    public void onFragmentInteraction(int page_num) {

        if(page_num != cur_page){
            switch (page_num){
                case 1:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance("http://ig28.com/ftcar", null)
                             )
                            .commit();
                    break;
                case 2:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, NewsFragment.newInstance("http://www.baidu.com", null)
                            )
                            .commit();
                    break;
                case 3:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance("http://ig28.com/mothertea2", null)
                            )
                            .commit();
                    break;
                case 4:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance("http://www.microsoft.com", null)
                            )
                            .commit();
                    break;
                case 5:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, MainPageFragment.newInstance("http://wximg.qq.com/tmt/_events/20150514-promo-vivo/dist/html/index.html", null)
                            )
                            .commit();
                    break;
                case 6:
                    WaitingDialog waiting = new WaitingDialog(this);
                    waiting.show();
                    break;
                case 7://赛程
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new FixtureFragment())
                            .commit();
                    break;
                case 8:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new SquadFragment())
                            .commit();
                    break;

            }
        }
        cur_page = page_num;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(drawer.isOpened() && !drawer.isMoving()){
                drawer.animateOpen();
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
        }
        return super.onKeyDown(keyCode, event);
    }

}
