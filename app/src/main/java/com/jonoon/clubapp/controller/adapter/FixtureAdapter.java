package com.jonoon.clubapp.controller.adapter;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jonoon.clubapp.R;
import com.jonoon.clubapp.model.bean.Fixture;
import com.jonoon.clubapp.model.bean.FixtureItem;
import com.jonoon.clubapp.util.net.VolleyHelper;
import com.jonoon.clubapp.view.titlelistview.PinnedHeaderListView;


public class FixtureAdapter extends BaseAdapter implements PinnedHeaderListView.PinnedHeaderAdapter,
		OnScrollListener {

    private Activity activity;
	private LayoutInflater inflater;

	private ArrayList<FixtureItem> data;
    private ImageLoader imageLoader;

	private int position_of_group = 0;

	public FixtureAdapter(Activity activity) {
        this.activity = activity;
		this.inflater = activity.getLayoutInflater();
        imageLoader= VolleyHelper.getInstance(activity).getImageLoader();
	}

    public void setData(Fixture fixture){
        data = fixture.getData();
        Collections.sort(data);
        notifyDataSetChanged();
    }

	@Override
	public int getCount() {
        if(data != null){
            return data.size();
        }else {
            return 0;
        }
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder;

		if (convertView == null) {
            convertView = inflater.inflate(R.layout.fixture_listview_section_item, null);

            mViewHolder = new ViewHolder();

            mViewHolder.header = (RelativeLayout) convertView.findViewById(R.id.header);

            mViewHolder.header_text = (TextView) convertView.findViewById(R.id.header_text);
            mViewHolder.league = (TextView) convertView.findViewById(R.id.game_name);
            mViewHolder.addr = (TextView) convertView.findViewById(R.id.arena);
            mViewHolder.vs = (ImageView) convertView.findViewById(R.id.vs_logo);

            mViewHolder.score_layout = (LinearLayout) convertView.findViewById(R.id.score_layout);
            mViewHolder.team1score = (TextView) convertView.findViewById(R.id.team1_score);
            mViewHolder.slash = (TextView) convertView.findViewById(R.id.slash);
            mViewHolder.team2score = (TextView) convertView.findViewById(R.id.team2_score);
            mViewHolder.week = (TextView) convertView.findViewById(R.id.week);
            mViewHolder.time = (TextView) convertView.findViewById(R.id.time);

            mViewHolder.team1 = (TextView) convertView.findViewById(R.id.team1_name);
            mViewHolder.team2 = (TextView) convertView.findViewById(R.id.team2_name);
            mViewHolder.team1logo = (NetworkImageView) convertView.findViewById(R.id.team1_logo);
            mViewHolder.team2logo = (NetworkImageView) convertView.findViewById(R.id.team2_logo);
            mViewHolder.fixture_item_bottom = convertView.findViewById(R.id.fixture_item_bottom);

            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }


        //设置字体颜色
        if(position%2 == 1){
            convertView.setBackgroundResource(R.drawable.bg_fixture_item_b);
            mViewHolder.league.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_b));
            mViewHolder.addr.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_b));
            mViewHolder.team1score.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_b));
            mViewHolder.slash.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_b));
            mViewHolder.team2score.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_b));
            mViewHolder.team1.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_b));
            mViewHolder.team2.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_b));
            mViewHolder.week.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.time.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.fixture_item_bottom.setBackgroundResource(R.drawable.bg_fixture_item_bottom_b);
        }else {
            convertView.setBackgroundResource(R.color.bg_fixture_item_a);
            mViewHolder.league.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.addr.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.team1score.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.slash.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.team2score.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.team1.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.team2.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.week.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.time.setTextColor(activity.getResources().getColor(R.color.text_color_fixture_item_a));
            mViewHolder.fixture_item_bottom.setBackgroundResource(R.drawable.bg_fixture_item_bottom_a);
        }

        FixtureItem i = data.get(position);
        //设置数据
        mViewHolder.header_text.setText(i.getMonthOfEnglishName() + " " +i.getYear());
        mViewHolder.league.setText(i.getLeague());
        mViewHolder.addr.setText(i.getAddr());

        if("0".equals(i.getIsstart()) && "0".equals(i.getIsend())){
            //比赛尚未开始，显示vs logo
            mViewHolder.vs.setVisibility(View.VISIBLE);
            mViewHolder.score_layout.setVisibility(View.GONE);
        }else {
            //比赛已开始或已结束，显示比分栏
            mViewHolder.vs.setVisibility(View.GONE);
            mViewHolder.score_layout.setVisibility(View.VISIBLE);
            mViewHolder.team1score.setText(i.getTeam1score());
            mViewHolder.team2score.setText(i.getTeam2score());
        }
        mViewHolder.week.setText(i.getWeek());
        mViewHolder.time.setText(i.getTime());
        mViewHolder.team1.setText(i.getTeam1());
        mViewHolder.team2.setText(i.getTeam2());
        mViewHolder.team1logo.setImageUrl(i.getTeam1logo(),imageLoader);
        mViewHolder.team2logo.setImageUrl(i.getTeam2logo(),imageLoader);

        //header是否显示
        if(getCount()>1 && position != 0){
            FixtureItem pre = data.get(position-1);
            if(i.getMonthOfEnglishName() != null &  i.getMonthOfEnglishName().equals(pre.getMonthOfEnglishName())){
                mViewHolder.header.setVisibility(View.GONE);
            }else {
                mViewHolder.header.setVisibility(View.VISIBLE);
            }
        }else {
            mViewHolder.header.setVisibility(View.VISIBLE);
        }

		return convertView;
	}

    class ViewHolder{
        public RelativeLayout header;
        public TextView header_text;

        public TextView league;
        public TextView addr;

        public ImageView vs;

        public LinearLayout score_layout;
        public TextView team1score;
        public TextView slash;
        public TextView team2score;
        public TextView week;
        public TextView time;


        public TextView team1;
        public com.android.volley.toolbox.NetworkImageView team1logo;

        public TextView team2;
        public com.android.volley.toolbox.NetworkImageView team2logo;

        public View fixture_item_bottom;

    }

	@Override
	public int getPinnedHeaderState(int position) {

        FixtureItem i = data.get(position);
        if(getCount()>1 && position != 0){
            FixtureItem next = data.get(position+1);
            if(i.getMonthOfEnglishName() != null &  i.getMonthOfEnglishName().equals(next.getMonthOfEnglishName())){
                return PINNED_HEADER_VISIBLE;
            }else {
                return PINNED_HEADER_PUSHED_UP;
            }
        }else {
            return PINNED_HEADER_VISIBLE;
        }
	}

	@Override
	public void configurePinnedHeader(View header, int position) {

//		if (lastItem != position) {
//			notifyDataSetChanged();
//		}
		((TextView) header.findViewById(R.id.header_text)).setText(
                data.get(position).getMonthOfEnglishName() + " " +data.get(position).getYear());
//		lastItem = position;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (view instanceof PinnedHeaderListView) {
			((PinnedHeaderListView) view).configureHeaderView(firstVisibleItem);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

}
