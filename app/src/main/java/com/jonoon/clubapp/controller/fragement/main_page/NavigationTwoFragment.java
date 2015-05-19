package com.jonoon.clubapp.controller.fragement.main_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jonoon.clubapp.R;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link NavigationTwoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavigationTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationTwoFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private Button bt5;


    private Button bt6;
    private Button bt7;
    private Button bt8;
    private Button bt9;
    private Button bt10;

    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";

    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;


    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     *
     * @return A new instance of fragment NavigationOneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationTwoFragment newInstance(String param1, String param2) {
        NavigationTwoFragment fragment = new NavigationTwoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NavigationTwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation_two, container, false);
        bt1 = (Button) view.findViewById(R.id.bt_1);
        bt2 = (Button) view.findViewById(R.id.bt_2);
        bt3 = (Button) view.findViewById(R.id.bt_3);
        bt4 = (Button) view.findViewById(R.id.bt_4);
        bt5 = (Button) view.findViewById(R.id.bt_5);

        bt6 = (Button) view.findViewById(R.id.bt_6);
        bt7 = (Button) view.findViewById(R.id.bt_7);
        bt8 = (Button) view.findViewById(R.id.bt_8);
        bt9 = (Button) view.findViewById(R.id.bt_9);
        bt10 = (Button) view.findViewById(R.id.bt_10);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);

        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt10.setOnClickListener(this);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int page_num) {
        if (mListener != null) {
            mListener.onFragmentInteraction(page_num);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_1:
                onButtonPressed(1);
                break;
            case R.id.bt_2:
                onButtonPressed(2);
                break;
            case R.id.bt_3:
                onButtonPressed(3);
                break;
            case R.id.bt_4:
                onButtonPressed(4);
                break;
            case R.id.bt_5:
                onButtonPressed(5);
                break;
            case R.id.bt_6:
                onButtonPressed(6);
                break;
            case R.id.bt_7:
                onButtonPressed(7);
                break;
            case R.id.bt_8:
                onButtonPressed(8);
                break;
            case R.id.bt_9:
                onButtonPressed(9);
                break;
            case R.id.bt_10:
                onButtonPressed(10);
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int page_num);
    }

}
