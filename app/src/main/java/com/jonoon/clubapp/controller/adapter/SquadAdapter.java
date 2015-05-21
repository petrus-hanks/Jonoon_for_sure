package com.jonoon.clubapp.controller.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.jonoon.clubapp.R;
import com.jonoon.clubapp.model.bean.Squad;
import com.jonoon.clubapp.model.bean.SquadItem;
import com.jonoon.clubapp.model.variable.SquadGroupData;
import com.jonoon.clubapp.util.StringHelper;
import com.jonoon.clubapp.util.net.VolleyHelper;
import com.jonoon.clubapp.view.titlelistview.PinnedHeaderExpandableListView;

import java.util.ArrayList;
import java.util.Collections;


public class SquadAdapter extends BaseExpandableListAdapter implements PinnedHeaderExpandableListView.PinnedHeaderAdapter,
		OnScrollListener{

    private Activity activity;
	private LayoutInflater inflater;

    private ImageLoader imageLoader;

    private ArrayList<SquadGroupData> mData = new ArrayList<SquadGroupData>();

	public SquadAdapter(Activity activity) {
        this.activity = activity;
		this.inflater = activity.getLayoutInflater();
        imageLoader= VolleyHelper.getInstance(activity).getImageLoader();
	}

    public void setData(Squad squad){
        ArrayList<SquadItem> data = squad.getData();
        Collections.sort(data);
        mData = GroupingData(data);

        notifyDataSetChanged();
    }


    /*对数据重新分组处理*/
    private ArrayList<SquadGroupData> GroupingData(ArrayList<SquadItem> data){

        ArrayList<SquadGroupData> ret = new ArrayList<SquadGroupData>();

        if(data !=null && data.size() > 0){

            String groupName = new String();

            for(SquadItem i : data){

                if(StringHelper.isEmpty(i.getGroup())){
                    //如果分组列表为空，则跳过，不记录
                    continue;
                }

                if(groupName.equals(i.getGroup())){
                    //组名相同，则从group list取出group，item添加到group对象的children里
                    ret.get(ret.size()-1).getChildren().add(i);
                }else {
                    groupName = i.getGroup();
                    //组名不同，新建group，向group中添加item，并添加group至group list
                    SquadGroupData groupData = new SquadGroupData();
                    groupData.setGroupName(i.getGroup());
                    groupData.getChildren().add(i);
                    ret.add(groupData);
                }
            }
        }
        return ret;
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mData.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.squad_listview_group_section, null);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.squad_listview_group_item, null);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
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

    }

	@Override
	public int getPinnedHeaderState(int position) {

        return PINNED_HEADER_PUSHED_UP;
	}

    @Override
    public void configurePinnedHeader(View header, int position) {


//		if (lastItem != position) {
//			notifyDataSetChanged();
//		}
//		((TextView) header.findViewById(R.id.header_text)).setText(
//                data.get(position).getMonthOfEnglishName() + " " +data.get(position).getYear());
        lastItem = position;
    }
    private int lastItem;


	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (view instanceof PinnedHeaderExpandableListView) {
			((PinnedHeaderExpandableListView) view).configureHeaderView(firstVisibleItem);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

}
