package com.jonoon.clubapp.util.net;

import com.android.volley.DefaultRetryPolicy;

/**
 * Created by runzhang.han on 2015/1/8 10:57.
 */
public class CustomerRetryPolicy extends DefaultRetryPolicy {

    /**用于设置最大超时时间，*/
    private static final int TIMEOUT_MS = 10000;

    public CustomerRetryPolicy() {
        super(TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }
}
