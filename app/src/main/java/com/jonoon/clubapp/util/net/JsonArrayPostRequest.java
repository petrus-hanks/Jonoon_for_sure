package com.jonoon.clubapp.util.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.jonoon.clubapp.util.L;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class JsonArrayPostRequest extends Request<JSONArray> {
	
	private Map<String, String> mMap;
	private Response.Listener<JSONArray> mListener;
	    
	public JsonArrayPostRequest(String url, Map<String, String> map, Response.Listener<JSONArray> listener,Response.ErrorListener errorListener) {
		super(Request.Method.POST, url, errorListener);
		// TODO Auto-generated constructor stub
		this.mMap=map;
		this.mListener=listener;
		
	}
	
	 @Override
	    protected Map<String, String> getParams() throws AuthFailureError {
	        return mMap;
	    }

	@Override
	protected void deliverResponse(JSONArray arg0) {
		// TODO Auto-generated method stub
		 mListener.onResponse(arg0);
	}

	@Override
	protected Response<JSONArray> parseNetworkResponse(NetworkResponse arg0) {
		// TODO Auto-generated method stub
		String jsonString="";
		try {
			jsonString = new String(arg0.data, HttpHeaderParser.parseCharset(arg0.headers));
			L.e("JsonArrayPostRequest", jsonString);
			return Response.success(new JSONArray(jsonString),HttpHeaderParser.parseCacheHeaders(arg0));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return Response.error(new ParseError(e));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return Response.error(new ParseError(e));
		}

       
	}

}
