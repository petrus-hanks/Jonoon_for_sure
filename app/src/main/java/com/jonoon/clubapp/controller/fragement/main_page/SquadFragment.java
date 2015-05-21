package com.jonoon.clubapp.controller.fragement.main_page;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jonoon.clubapp.R;
import com.jonoon.clubapp.controller.adapter.SquadAdapter;
import com.jonoon.clubapp.model.bean.Squad;
import com.jonoon.clubapp.model.constants.ServerUrl;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.util.net.GsonRequest;
import com.jonoon.clubapp.util.net.VolleyHelper;
import com.jonoon.clubapp.view.custom_view.WaitingDialog;
import com.jonoon.clubapp.view.titlelistview.PinnedHeaderExpandableListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 */
public class SquadFragment extends android.support.v4.app.Fragment {

    private final String TAG = this.getClass().getSimpleName();
    private SquadAdapter adapter;
    private PinnedHeaderExpandableListView listView;

    private static final String URL = "mUrl";
    private static final String ARG_PARAM2 = "param2";

    private String mUrl;
    private String mParam2;

    private WaitingDialog mWaitingDialog;

    public SquadFragment() {
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

        LinearLayout frame = (LinearLayout) inflater.inflate(R.layout.fragment_squad, container, false);
        TextView title = (TextView) frame.findViewById(R.id.title);
        title.setText("足球队");
        ImageView right_icon = (ImageView) frame.findViewById(R.id.setting);
        right_icon.setImageResource(R.drawable.ic_user_center);

        adapter = new SquadAdapter(getActivity());
        listView = (PinnedHeaderExpandableListView) frame.findViewById(R.id.section_list_view);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(adapter);
        listView.setPinnedHeaderView(inflater.inflate(
                R.layout.squad_listview_group_section, listView, false));

        getData();

        return frame;
    }


    private void getData(){
        mWaitingDialog = new WaitingDialog(getActivity());
        mWaitingDialog.show();
        GsonRequest<Squad> jsObjRequest = new GsonRequest<Squad>(ServerUrl.getSQUAD_LIST(),
                Squad.class, null, new Response.Listener<Squad>() {
            @Override
            public void onResponse(Squad squad) {
                L.e(TAG, squad.toString());
                adapter.setData(squad);
                mWaitingDialog.cancel();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                L.e(TAG, volleyError.toString());
                mWaitingDialog.cancel();
            }
        });

        jsObjRequest.setTag(TAG);
        // Access the RequestQueue through your singleton class.
        VolleyHelper.getInstance(getActivity()).addToRequestQueue(jsObjRequest);
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
        mWaitingDialog.cancel();
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
