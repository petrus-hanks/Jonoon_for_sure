package com.jonoon.clubapp.controller.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;

import com.jonoon.clubapp.R;
import com.jonoon.clubapp.controller.adapter.IntroductionAdapter;
import com.jonoon.clubapp.view.custom_view.viewflow.CircleFlowIndicator;
import com.jonoon.clubapp.view.custom_view.viewflow.ViewFlow;

public class IntroductionActivity extends Activity {


    private ViewFlow viewFlow;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_introduction);

        viewFlow = (ViewFlow) findViewById(R.id.view_flow);
        viewFlow.setAdapter(new IntroductionAdapter(this), 0);
        CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.view_flow_indicator);

        viewFlow.setFlowIndicator(indic);
    }
    /* If your min SDK version is < 8 you need to trigger the onConfigurationChanged in ViewFlow manually, like this */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        viewFlow.onConfigurationChanged(newConfig);
    }
}
