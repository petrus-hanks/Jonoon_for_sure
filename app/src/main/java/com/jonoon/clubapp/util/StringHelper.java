package com.jonoon.clubapp.util;

import java.util.ArrayList;

public class StringHelper {

	public final static boolean isEmpty(String msg) {
		if (msg == null || msg.trim().length() == 0) {
			return true;
		}
		return false;
	}

    public final static String transArrayList2String(ArrayList<String> arrayList){

        if(arrayList == null){
            return null;
        }
        String ret = arrayList.toString();
        return ret.substring(ret.indexOf("[")+1,ret.lastIndexOf("]"));
    }
}
