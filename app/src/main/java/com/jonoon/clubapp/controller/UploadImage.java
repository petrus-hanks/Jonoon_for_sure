package com.jonoon.clubapp.controller;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jonoon.clubapp.model.constants.ServerUrl;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.util.net.MultiPartRequest;
import com.jonoon.clubapp.util.net.VolleyHelper;
import com.jonoon.clubapp.util.storage.StorageHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/6/9 13:11
 */
public class UploadImage {

    private static final String TAG = "UploadImage";

    public static void upload(Activity activity, String imagePath){

        L.e(TAG, "upload do");
        File imageFile = new File(imagePath);

        MultiPartRequest image_request = new MultiPartRequest(ServerUrl.getUpload(),
                imageFile, StorageHelper.getPhotoFileName(imagePath), upload_listener, errorListener );
        image_request.setTag(TAG);
        VolleyHelper.getInstance(activity).addToRequestQueue(image_request);
    }

    private static Response.Listener<JSONObject> upload_listener = new Response.Listener<JSONObject>() {

        @Override
        public void onResponse(JSONObject response) {
            L.e(TAG,response.toString());

            try {
                String msg = response.getString("msg");
                L.e(TAG,msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            L.e(TAG, "upload_listener success");
        }
    };

    private static Response.ErrorListener errorListener = new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
                L.e(TAG,error.toString());
        }
    };

}
