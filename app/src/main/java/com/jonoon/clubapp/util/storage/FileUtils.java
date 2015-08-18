package com.jonoon.clubapp.util.storage;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtils {

	private static final String TAG = "FileUtils";
	private static final boolean DEBUG = false;
	
	public static String getString(InputStream inputStream) {
	    InputStreamReader inputStreamReader = null;
	    try {  
	        inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	    } catch (UnsupportedEncodingException e1) {
	        e1.printStackTrace();  
	    }  
	    BufferedReader reader = new BufferedReader(inputStreamReader);
	    StringBuffer sb = new StringBuffer("");
	    String line;
	    try {  
	        while ((line = reader.readLine()) != null) {  
	            sb.append(line);  
	            sb.append("\n");  
	        }  
	    } catch (IOException e) {
	    	if(DEBUG) e.printStackTrace();  
	    }  
	    if(DEBUG) Log.e(TAG, "String buffer = " + sb.toString());
	    return sb.toString();  
	}  
	
	public static String readTextFile(File file){
		
		String ret = new String();
		try {
			ret = getString(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			if(DEBUG) e.printStackTrace();
		}
		
		return ret;
		
	}
	
	public static boolean writeTextFile(File file, String data){
        
		if (file.exists()) {
            file.delete();
        }
		 FileWriter fw;
		try {
			fw = new FileWriter(file, false);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(data);  
	        bw.close();  
	        fw.close();  
		} catch (IOException e) {
			if(DEBUG) e.printStackTrace();
		}  

		return false;
		
	}


	/**
	 * Copies a private raw resource content to a publicly readable
	 * file such that the latter can be shared with other applications.
	 */
	public static String copyPrivateRawResourceToPubliclyAccessibleFile(Context con, int RawResourceId, String filename) {

		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		String ret_value = new String();
		try {
			inputStream = con.getResources().openRawResource(RawResourceId);
			outputStream = con.openFileOutput(filename, Context.MODE_PRIVATE);
			byte[] buffer = new byte[1024];
			int length = 0;
			try {
				while ((length = inputStream.read(buffer)) > 0){
					outputStream.write(buffer, 0, length);
				}
			} catch (IOException ioe) {
				if(DEBUG) ioe.printStackTrace();
			}

		} catch (FileNotFoundException fnfe) {
			if(DEBUG) fnfe.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException ioe) {
				if(DEBUG) ioe.printStackTrace();
			}
			try {
				outputStream.close();
				ret_value = con.getFileStreamPath(filename).getPath();
			} catch (IOException ioe) {
				if(DEBUG) ioe.printStackTrace();
			} catch (Exception e) {
				if(DEBUG) e.printStackTrace();
			}

		}

		return ret_value;
	}
}
