package com.jonoon.clubapp.view.custom_view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jonoon.clubapp.R;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/18 10:39
 */
public class WaitingDialog {

    Dialog loadingDialog;
    AnimationDrawable frameAnimation;
    Context mContext;
    public WaitingDialog(Context con) {

        mContext = con;

        ImageView anim_holder = new ImageView(mContext);
        anim_holder.setBackgroundResource(R.drawable.waiting_anim);
        frameAnimation = (AnimationDrawable) anim_holder.getBackground();

        loadingDialog = new Dialog(mContext, R.style.loading_dialog);
        loadingDialog.setCancelable(true);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(anim_holder
                , new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void show(){

        if(loadingDialog != null && frameAnimation != null){
            loadingDialog.show();
            frameAnimation.start();
        }else {
            ImageView anim_holder = new ImageView(mContext);
            anim_holder.setBackgroundResource(R.drawable.waiting_anim);
            frameAnimation = (AnimationDrawable) anim_holder.getBackground();

            loadingDialog = new Dialog(mContext, R.style.loading_dialog);
            loadingDialog.setCancelable(true);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setContentView(anim_holder
                    , new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    public void hide(){
        if(loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.hide();
        }
    }


    public void cancel(){
        if(loadingDialog != null){
            loadingDialog.cancel();
        }
    }
}
