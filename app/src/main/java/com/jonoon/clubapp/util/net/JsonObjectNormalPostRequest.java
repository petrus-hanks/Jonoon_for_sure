package com.jonoon.clubapp.util.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jonoon.clubapp.util.L;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by runzhang.han on 2015/1/6 12:44.
 */
public class JsonObjectNormalPostRequest extends Request<JSONObject> {

	 private final String TAG = "JsonObjectNormalPostRequest";
	private Map<String, String> mMap;
    private Response.Listener<JSONObject> mListener;

    public JsonObjectNormalPostRequest(String url, Map<String, String> map, Response.Listener<JSONObject> listener,Response.ErrorListener errorListener) {
        super(Request.Method.POST, url, errorListener);
        setRetryPolicy(new CustomerRetryPolicy());
        mListener = listener;
        mMap = map;
    }

    //mMap是已经按照前面的方式,设置了参数的实例
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }

    //此处因为response返回值需要json数据,和JsonObjectRequest类一样即可
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            L.e(TAG, jsonString);
            return Response.success(new JSONObject(jsonString),HttpHeaderParser.parseCacheHeaders(response));
            
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }
    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }
}
