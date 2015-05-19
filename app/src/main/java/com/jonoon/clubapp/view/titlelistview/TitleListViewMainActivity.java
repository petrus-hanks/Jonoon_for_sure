package com.jonoon.clubapp.view.titlelistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jonoon.clubapp.R;
import com.jonoon.clubapp.model.bean.Fixture;
import com.jonoon.clubapp.model.bean.FixtureItem;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.util.net.GsonRequest;
import com.jonoon.clubapp.util.net.VolleyHelper;

/**
 * ListView循环更换标题效果
 * @author Administrator
 *
 */
public class TitleListViewMainActivity extends Activity {

    private final String TAG = this.getClass().getSimpleName();
	private TestAdapter adapter;
	private PinnedHeaderListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setView();
        initView();
        testvolley();
    }

    public void setView() {
		setContentView(R.layout.activity_custom_title_listview_main);
		
	}

	public void initView() {
		adapter = new TestAdapter(this);

		listView = (PinnedHeaderListView) findViewById(R.id.section_list_view);
		listView.setAdapter(adapter);
		listView.setOnScrollListener(adapter);
		listView.setPinnedHeaderView(getLayoutInflater().inflate(
				R.layout.activity_custom_title_listview_section, listView, false));
		
	}

    private void testvolley(){

        GsonRequest<Fixture> jsObjRequest = new GsonRequest<Fixture>("http://jonoon.ig28.com/api/fixture",
                Fixture.class, null, new Response.Listener<Fixture>() {
            @Override
            public void onResponse(Fixture fixture) {
                L.e(TAG, fixture.toString());
                adapter.setData(fixture);
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        jsObjRequest.setTag(TAG);
        // Access the RequestQueue through your singleton class.
        VolleyHelper.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}
