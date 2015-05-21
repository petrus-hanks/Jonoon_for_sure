package com.jonoon.clubapp.view.titlelistview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jonoon.clubapp.controller.adapter.SquadAdapter;
import com.jonoon.clubapp.model.bean.Squad;

public class PinnedHeaderExpandableListView extends ExpandableListView {

	public interface PinnedHeaderAdapter {

		public static final int PINNED_HEADER_GONE = 0;
		public static final int PINNED_HEADER_VISIBLE = 1;
		public static final int PINNED_HEADER_PUSHED_UP = 2;

		int getPinnedHeaderState(int position);

		void configurePinnedHeader(View header, int position);
	}

	private static final int MAX_ALPHA = 255;


	private View mHeaderView;
	private boolean mHeaderViewVisible;
	private int mHeaderViewWidth;
	private int mHeaderViewHeight;

	public PinnedHeaderExpandableListView(Context context) {
		super(context);
	}

	public PinnedHeaderExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PinnedHeaderExpandableListView(Context context, AttributeSet attrs,
                                          int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setPinnedHeaderView(View view) {
		mHeaderView = view;
        mHeaderView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
		if (mHeaderView != null) {
			setFadingEdgeLength(0);
		}
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		if (mHeaderView != null) {
			measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
			mHeaderViewWidth = mHeaderView.getMeasuredWidth();
			mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (mHeaderView != null) {
			mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
			configureHeaderView(getFirstVisiblePosition());
		}
	}

	public void configureHeaderView(int position) {

        if(getCount() == 0){
            return;
        }
		if (mHeaderView == null) {
			return;
		}
		int state = ((PinnedHeaderAdapter)getExpandableListAdapter()).getPinnedHeaderState(position);
		switch (state) {
		case PinnedHeaderAdapter.PINNED_HEADER_GONE: {
            mHeaderViewVisible = false;
			break;
		}

		case PinnedHeaderAdapter.PINNED_HEADER_VISIBLE: {
            mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
            ((PinnedHeaderAdapter)getExpandableListAdapter()).configurePinnedHeader(mHeaderView, position);
            mHeaderViewVisible = true;
			break;
		}

		case PinnedHeaderAdapter.PINNED_HEADER_PUSHED_UP: {
			View firstView = getChildAt(0);
			int bottom = firstView.getBottom();
			int headerHeight = mHeaderView.getHeight()
                    -mHeaderView.getPaddingBottom();//add for the shadow of the header view by hrz 2015.5.20
			int y;
			if (bottom < headerHeight) {
				y = (bottom - headerHeight);
			} else {
				y = 0;
			}
            ((PinnedHeaderAdapter)getExpandableListAdapter()).configurePinnedHeader(mHeaderView, position);
			if (mHeaderView.getTop() != y) {
				mHeaderView.layout(0, y, mHeaderViewWidth, mHeaderViewHeight
						+ y);
			}
			mHeaderViewVisible = true;
			break;
		}
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mHeaderViewVisible) {
			drawChild(canvas, mHeaderView, getDrawingTime());
		}
	}
}