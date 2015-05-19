package com.jonoon.clubapp.util.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jonoon.clubapp.util.L;


import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by runzhang.han on 2014/11/13.
 */
public class GsonRequest<T> extends Request<T> {

    private final String TAG = this.getClass().getSimpleName();

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String,String> headers;

    /** Charset for request. */
    private static final String PROTOCOL_CHARSET = "utf-8";

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private Map<String, String> mMap;
 

    private String mRequestBody;
    private final Response.Listener<T> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, null, clazz, null, listener, errorListener);
    }


    public GsonRequest(int method, String url, Map<String, String> map, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        setRetryPolicy(new CustomerRetryPolicy());
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.mMap = (map == null) ? null : map;
    }
    
    

    @Override
    public Map<String,String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            L.e(TAG, "json=" + json);
            return Response.success(
                    gson.fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
            
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

   // mMap是已经按照前面的方式,设置了参数的实例
    @Override
    protected Map<String,String> getParams() throws AuthFailureError {
        return mMap;
    }

//    @Override
//    public String getBodyContentType() {
//
//        return PROTOCOL_CONTENT_TYPE;
//    }
//
//    @Override
//    public byte[] getBody() {
//
//        try {
//            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
//        } catch (UnsupportedEncodingException uee) {
//            return null;
//        }
//    }

}