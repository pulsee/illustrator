package com.kuks.illustratorlib.graphics;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

/**
 * Entry class for library
 */
public class IllustratorLib {


    private volatile static IllustratorLib sIllustratorLib;

    private IllustratorLib(){}//force user to use static method to get singleton


    /**
     * Get the singleton.
     *
     * @return the singleton
     */
    public static IllustratorLib getInstance() {

        if (sIllustratorLib == null) {

            //double check for multi threading
            synchronized (IllustratorLib.class) {
                if (sIllustratorLib == null) {
                    sIllustratorLib = new IllustratorLib();
                }
            }
        }

        return sIllustratorLib;
    }


   public Bitmap getBitmap(@NonNull Drawable drawable) {
       return IllustratorBitmapFactory.fromDrawable(drawable);
    }



}
