/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.jonoon.clubapp.util.storage;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
import com.jonoon.clubapp.util.ScreenInfo;


/**
 * An image memory cache implementation for Volley which allows the retrieval of entires via a URL.
 * Volley internally inserts items with a key which is a combination of a the size of the image,
 * and the url.
 *
 * This class provide the method {@li#nk #getBitmapFromUrl(String)} which allows the retrieval of
 * a bitmap solely on the URL.
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

    // Cache the last created snapshot
//    private Map<String, Bitmap> mLastSnapshot;

    /*recommended to use LruBitmapCache(Context ctx) as default*/
    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }

    public LruBitmapCache(Context ctx) {
        this(getCacheSize(ctx));
    }

    // Returns a cache size equal to approximately three screens worth of images.
    public static int getCacheSize(Context ctx) {
        final int pixel_of_screen = ScreenInfo.getInstance(ctx).pixel_of_screen;
        // 4 bytes per pixel
        final int screenBytes = pixel_of_screen * 4;

        return screenBytes * 3;
    }

    @Override
    public Bitmap getBitmap(String key) {
        return get(key);
    }

    @Override
    public void putBitmap(String key, Bitmap bitmap) {
        put(key, bitmap);

        // An entry has been added, so invalidate the snapshot
//        mLastSnapshot = null;
    }

    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        super.entryRemoved(evicted, key, oldValue, newValue);

        // An entry has been removed, so invalidate the snapshot
//        mLastSnapshot = null;
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getByteCount();
    }


//    public Bitmap getBitmapFromUrl(String url) {
//        // If we do not have a snapshot to use, generate one
//        if (mLastSnapshot == null) {
//            mLastSnapshot = snapshot();
//        }
//
//        // Iterate through the snapshot to find any entries which match our url
//        for (Map.Entry<String, Bitmap> entry : mLastSnapshot.entrySet()) {
//            if (url.equals(extractUrl(entry.getKey()))) {
//                // We've found an entry with the same url, return the bitmap
//                return entry.getValue();
//            }
//        }
//
//        // We didn't find an entry, so return null
//        return null;
//    }
//
//    private static String extractUrl(String key) {
//        return key.substring(key.indexOf("http"));
//    }
}

