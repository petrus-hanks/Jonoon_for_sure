package com.jonoon.clubapp.controller.adapter;
/*
 * Copyright (C) 2011 Patrik �kerfeldt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.jonoon.clubapp.R;
import com.jonoon.clubapp.controller.activity.MainActivity;


public class IntroductionAdapter extends BaseAdapter {

    private Context con;
	private LayoutInflater mInflater;
	private static final int[] ids = { R.drawable.intro_1,
            R.drawable.intro_2,
            R.drawable.intro_3};

	public IntroductionAdapter(Context context) {
        con = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return ids.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.activity_intro, null);
		}
		((ImageView) convertView.findViewById(R.id.image_view)).setImageResource(ids[position]);
        if(position == ids.length-1){
            Button go = (Button) convertView.findViewById(R.id.bt_go_on);
            go.setVisibility(View.VISIBLE);
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent go = new Intent(con.getApplicationContext(), MainActivity.class);
                    con.startActivity(go);
                }
            });
        }
		return convertView;
	}

}
