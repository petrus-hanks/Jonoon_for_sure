package com.jonoon.clubapp.view.list_view_animation.swinginadapters.prepared;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jonoon.clubapp.view.list_view_animation.swinginadapters.AnimationAdapter;
import com.nineoldandroids.animation.Animator;

public class AlphaInAnimationAdapter extends AnimationAdapter {

	public AlphaInAnimationAdapter(BaseAdapter baseAdapter) {
		super(baseAdapter);
	}

	@Override
	protected long getAnimationDelayMillis() {
		return DEFAULTANIMATIONDELAYMILLIS;
	}

	@Override
	protected long getAnimationDurationMillis() {
		return DEFAULTANIMATIONDURATIONMILLIS;
	}

	@Override
	public Animator[] getAnimators(ViewGroup parent, View view) {
		return new Animator[0];
	}
}
