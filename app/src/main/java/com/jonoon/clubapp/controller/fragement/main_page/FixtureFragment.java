package com.jonoon.clubapp.controller.fragement.main_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jonoon.clubapp.R;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.view.custom_view.H5WebView;
import com.jonoon.clubapp.view.titlelistview.PinnedHeaderListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {
 * Use the {@link com.jonoon.clubapp.controller.fragement.main_page.FixtureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FixtureFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();

    private static final String URL = "mUrl";
    private static final String ARG_PARAM2 = "param2";

    private String mUrl;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment MainPageFragment.
     */
    public static FixtureFragment newInstance(String param1, String param2) {
        FixtureFragment fragment = new FixtureFragment();
        Bundle args = new Bundle();
        args.putString(URL, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FixtureFragment() {
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
        LinearLayout frame = (LinearLayout) inflater.inflate(R.layout.fragment_fixture, container, false);
        TextView title = (TextView) frame.findViewById(R.id.title);
        title.setText("赛 程");
        ImageView right_icon = (ImageView) frame.findViewById(R.id.setting);
        right_icon.setImageResource(R.drawable.ic_user_center);
        PinnedHeaderListView list = (PinnedHeaderListView) frame.findViewById(R.id.section_list_view);

        return frame;
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
    }

    @Override
    public void onResume() {
        super.onResume();
        L.e(TAG, "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e(TAG, "onDestroy");
    }


}
