package com.jonoon.clubapp.controller.fragement.main_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jonoon.clubapp.R;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.view.custom_view.H5WebView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {
 * Use the {@link MainPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPageFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    private static final String URL = "mUrl";
    private static final String ARG_PARAM2 = "param2";

    private String mUrl;
    private String mParam2;

    private H5WebView wv;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment MainPageFragment.
     */
    public static MainPageFragment newInstance(String param1, String param2) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();
        args.putString(URL, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public MainPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(URL);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout frame = (LinearLayout) inflater.inflate(R.layout.fragment_main_page, container, false);
        TextView title = (TextView) frame.findViewById(R.id.title);
        title.setText("标题");
        wv = (H5WebView) frame.findViewById(R.id.webview);
        wv.loadUrl(mUrl);

        return frame;
    }

    public boolean canGoBack(){
        return wv.canGoBack();
    }

    public void goBack(){
        if(wv.canGoBack()){
            wv.goBack();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        L.e(TAG,"onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.e(TAG,"onDetach");
    }

    @Override
    public void onPause() {
        super.onPause();
        L.e(TAG, "onPause");
        wv.onPause();
        wv.pauseTimers();
    }

    @Override
    public void onResume() {
        super.onResume();
        L.e(TAG, "onResume");
        wv.onResume();
        wv.resumeTimers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e(TAG, "onDestroy");
        wv.stopLoading();
        wv.setWebChromeClient(null);
        wv.setWebViewClient(null);
        wv.destroy();
        wv = null;
    }
}
