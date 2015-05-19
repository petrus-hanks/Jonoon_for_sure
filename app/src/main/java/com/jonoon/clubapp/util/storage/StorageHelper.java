package com.jonoon.clubapp.util.storage;

import android.os.Environment;


import com.jonoon.clubapp.util.DateHelper;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.util.StringHelper;

import java.io.File;

/**
 * 文件存储相关
 * Created by runzhang.han on 2014/10/29.
 */
public class StorageHelper {

    private static final String LOG_TAG = "StorageHelper";

    private static final String PHOTO_FILE_NAME = "symptom";
    private static final String PHOTO_FILE_SUFFIX = ".jpg";

    private static final String ALBUM_NAME = "病象照片";

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            L.e(LOG_TAG, "ExternalStorage is Writable");
            return true;
        }
        L.e(LOG_TAG, "ExternalStorage is not Writable");
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /*获取相册目录
    * @return: File
    * */
    public static File getAlbumStorageDir(){
        return getAlbumStorageDir(ALBUM_NAME);
    }

    /*获取相册目录
    * @return: File
    * */
    public static File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            L.e(LOG_TAG, "Directory not created, maybe exist");
        }
        return file;
    }

    /*
    * 生成图片文件名
    * return:file_name
    * */
    public static String generatePhotoFileName(){

        String file_name = StorageHelper.PHOTO_FILE_NAME
                + DateHelper.getTime()
                + StorageHelper.PHOTO_FILE_SUFFIX;
        return file_name;
    }

    /*
    * 生成文件绝对路径
    * return:file_path
    * */
    public static String generatePhotoFilePath(){

        String file_path = getAlbumStoragePath()+"/"+ generatePhotoFileName();

        return file_path;
    }

    /*
    *生成相册路径
    * */
    public static String getAlbumStoragePath() {
        return getAlbumStoragePath(ALBUM_NAME);
    }

    /*
    *生成相册路径
    * param:String albumName
    * */
    public static String getAlbumStoragePath(String albumName) {
        // Get the directory for the user's public pictures directory.
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+albumName;

        File file = new File(path);
        if (!file.mkdirs()) {
            L.e(LOG_TAG, "Directory not created, maybe exist");
        }
        return path;
    }

    public static String getPhotoFileName(String filePath){

        if(StringHelper.isEmpty(filePath)){
            return generatePhotoFileName();
        }
        if (!filePath.contains("/")){
            return generatePhotoFileName();
        }
        String name = filePath.substring(filePath.lastIndexOf("/")+1);
        L.e(LOG_TAG,name);

        return name;
    }
}
