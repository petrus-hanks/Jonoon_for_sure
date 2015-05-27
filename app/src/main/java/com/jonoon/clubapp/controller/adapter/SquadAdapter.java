package com.jonoon.clubapp.controller.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
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
import com.jonoon.clubapp.view.titlelistview.AnimatedExpandableListView;

import java.util.ArrayList;
import java.util.Collections;


public class SquadAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter
        implements AnimatedExpandableListView.PinnedHeaderAdapter,
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

    public ArrayList<SquadGroupData> getData(){
        return mData;
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
        TextView group_title = (TextView) convertView.findViewById(R.id.group_title);
        SquadGroupData data = mData.get(groupPosition);
        group_title.setText(data.getGroupName());
        ImageView group_indicator = (ImageView) convertView.findViewById(R.id.expand_indicator);
        if(isExpanded){
            group_indicator.setImageResource(R.drawable.ic_group_expanded_state);
        }else {
            group_indicator.setImageResource(R.drawable.ic_group_collapse_state);
        }
        return convertView;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.squad_listview_group_item, null);
            vh = new ViewHolder();
            vh.player_avast = (NetworkImageView) convertView.findViewById(R.id.player_avast);
            vh.player_number = (TextView) convertView.findViewById(R.id.player_number);
            vh.player_position = (TextView) convertView.findViewById(R.id.player_position);
            vh.player_name = (TextView) convertView.findViewById(R.id.player_name);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        SquadItem data = mData.get(groupPosition).getChildren().get(childPosition);
        vh.player_avast.setImageUrl(data.getAvatar(),imageLoader);
        vh.player_number.setText(data.getNo());
        vh.player_position.setText(data.getPosition());
        vh.player_name.setText(data.getName());

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return mData.get(groupPosition).getChildren().size();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder{
        public ImageView group_indicator;
        public TextView group_title;

        public NetworkImageView player_avast;
        public TextView player_number;

        public TextView player_position;
        public TextView player_name;

    }

    /*header is group title ,so group_position is needed*/
    @Override
    public void configurePinnedHeader(View header, int group_position, boolean isExpanded) {

        SquadGroupData data = mData.get(group_position);
        TextView group_title = (TextView) header.findViewById(R.id.group_title);
        group_title.setText(data.getGroupName());
        if(isExpanded){
            ((ImageView)header.findViewById(R.id.expand_indicator)).setImageResource(R.drawable.ic_group_expanded_state);
        }else {
            ((ImageView)header.findViewById(R.id.expand_indicator)).setImageResource(R.drawable.ic_group_collapse_state);
        }

    }



    @Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		if (view instanceof AnimatedExpandableListView) {
			((AnimatedExpandableListView) view).configureHeaderView(firstVisibleItem);
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

}
