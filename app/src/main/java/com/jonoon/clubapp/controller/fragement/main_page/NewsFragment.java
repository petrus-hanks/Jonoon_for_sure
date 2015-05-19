package com.jonoon.clubapp.controller.fragement.main_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jonoon.clubapp.R;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.view.custom_view.H5WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
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
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(URL, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFragment() {
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
        LinearLayout frame = (LinearLayout) inflater.inflate(R.layout.fragment_news, container, false);
        title_layout = (RelativeLayout) frame.findViewById(R.id.title_layout);
        TextView title = (TextView) frame.findViewById(R.id.title);
        title.setText("标题");
        wv = (H5WebView) frame.findViewById(R.id.webview);
        wv.loadUrl(mUrl);

        ImageView btn_menu = (ImageView) frame.findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectNumberDialog();
            }
        });

        return frame;
    }

    private RelativeLayout title_layout;
    private List<String> numbers;
    private NumbersAdapter adapter;
    private PopupWindow popupWindow;
    /**
     * 弹出选择号码对话框
     */
    private void showSelectNumberDialog() {
        numbers = getNumbers();

        ListView lv = new ListView(getActivity());
        lv.setBackgroundResource(R.color.green);
        // 隐藏滚动条
        lv.setVerticalScrollBarEnabled(false);
        // 让listView没有分割线
        lv.setDividerHeight(0);
        lv.setDivider(null);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), "ListView的第" + position + "个Item被点击了..", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

        adapter = new NumbersAdapter();
        lv.setAdapter(adapter);


        popupWindow = new PopupWindow(lv, title_layout.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置点击外部可以被关闭
        popupWindow.setOutsideTouchable(true);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.transparent));
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.showAsDropDown(title_layout, 0, 0);		// 显示


    }

    private List<String> getNumbers() {
        List<String> numbers = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            numbers.add("Animation" + i);
        }
        return numbers;
    }

    class NumbersAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return numbers.size();
        }

        @Override
        public Object getItem(int position) {
            return numbers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NumberViewHolder mHolder = null;
            if(convertView == null) {
                mHolder = new NumberViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_spinner_numbers, null);
                mHolder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
                mHolder.ibDelete = (ImageButton) convertView.findViewById(R.id.ib_delete);
                convertView.setTag(mHolder);
            } else {
                mHolder = (NumberViewHolder) convertView.getTag();
            }

            mHolder.tvNumber.setText(numbers.get(position));
            mHolder.ibDelete.setTag(position);
            mHolder.ibDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int index = (Integer) v.getTag();
                    numbers.remove(index);
                    adapter.notifyDataSetChanged();

                    if(numbers.size() == 0) {
                        popupWindow.dismiss();
                    }
                }
            });

            return convertView;
        }

    }

    public class NumberViewHolder {
        public TextView tvNumber;
        public ImageButton ibDelete;
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
        L.e(TAG, "onAttach");
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
