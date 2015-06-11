package com.jonoon.clubapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jonoon.clubapp.R;
import com.jonoon.clubapp.model.constants.ServerUrl;
import com.jonoon.clubapp.util.L;

public class BaseActivityWithJavaScriptInterface extends FragmentActivity {

    private final String TAG = this.getClass().getSimpleName();

    public static final int REQUEST_IMAGE_URI = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        L.e(TAG,"requestCode = " + requestCode +" resultCode = "+resultCode);

        if(resultCode == RESULT_OK){
            switch (requestCode) {
                case REQUEST_IMAGE_URI:
                    L.e(TAG, "REQUEST_IMAGE_URI");
                    Uri uri = data.getData();

                    int actual_image_column_index;
                    String img_path;
                    String[] proj = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
                    actual_image_column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    img_path = cursor.getString(actual_image_column_index);

                    UploadImage.upload(this, img_path);

                    myAsyn m = new myAsyn();
                    String[] param = {img_path, ServerUrl.getUpload()};
                    m.execute(param);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    class myAsyn extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            UploadFile.sendFileToServer(params[0 ],params[1]);
            return null;
        }
    }
}
