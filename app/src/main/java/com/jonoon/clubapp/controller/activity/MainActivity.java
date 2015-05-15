package com.jonoon.clubapp.controller.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.FrameLayout;

import com.jonoon.clubapp.MyApplication;
import com.jonoon.clubapp.R;
import com.jonoon.clubapp.controller.adapter.ViewPager4SlidingDrawer;
import com.jonoon.clubapp.controller.fragement.main_page.MainPageFragment;
import com.jonoon.clubapp.controller.fragement.main_page.NavigationOneFragment;
import com.jonoon.clubapp.controller.fragement.main_page.NavigationTwoFragment;
import com.jonoon.clubapp.controller.fragement.main_page.NewsFragment;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.view.custom_view.slidingdrawer.WrappingSlidingDrawer;

public class MainActivity extends FragmentActivity
        implements NavigationOneFragment.OnFragmentInteractionListener,
        NavigationTwoFragment.OnFragmentInteractionListener{

    private final String TAG = this.getClass().getSimpleName();
    private ViewPager pager;
    private WrappingSlidingDrawer drawer;
    private int cur_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        if(((MyApplication)getApplication()).goToIntroduction){
            Intent go = new Intent(getApplicationContext(), IntroductionActivity.class);
            startActivity(go);
            ((MyApplication)getApplication()).goToIntroduction = false;
        }

        initView();

    }


    private void initView(){
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(new ViewPager4SlidingDrawer(getSupportFragmentManager()));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, NewsFragment.newInstance("http://ig28.com/mothertea2", null)
                )
                .commit();

        drawer = (WrappingSlidingDrawer) findViewById(R.id.drawer);

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
            }
        }
        cur_page = page_num;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(drawer.isOpened()){
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
