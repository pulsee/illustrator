package com.kuks.illustrator.bindings;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.kuks.illustrator.R;
import com.kuks.illustrator.data.Asset;
import com.kuks.illustrator.utils.Constants;
import com.kuks.illustratorlib.graphics.IllustratorBitmapFactory;
import com.kuks.illustratorlib.graphics.IllustratorLib;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import androidx.databinding.BindingAdapter;

/**
 * bind "imageUrl" attribute in xml layout with corresponding method in class
 */
public class ImageViewBindings {

    /**
     * Sets an image from asset/drawable folder
     * @param imageView view on which to set image
     * @param asset asset object
     */
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView,Asset asset) {

        Context context = imageView.getContext();
        try {
            Drawable drawable=null;

            if (asset.getDrawable_type()==Constants.DRAWABLE_TYPE.DRAWABLE) {
                int resourceID = context.getResources().getIdentifier(asset.getName(), "drawable", context.getPackageName());
                drawable = context.getResources().getDrawable(resourceID);
            } else if (asset.getDrawable_type()==Constants.DRAWABLE_TYPE.ASSET)
            {
                drawable = Drawable.createFromStream(context.getAssets().open(asset.getName()), null);
            }

            /**
             * call Library method to convert drawable to bitmap
             */
            imageView.setImageBitmap(IllustratorLib.getInstance().getBitmap(drawable));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*imageView.setImageBitmap(
                decodeSampledBitmapFromResource(context.getResources(), R.id.myimage, 100, 100));*/

       // Picasso.get().load("file:///android_asset/"+asset.getName()).into(imageView);

        /*Glide.with(imageView.getContext())
                .load(Uri.parse("file:///android_asset/"+asset.getName()))
                .into(imageView);*/
    }


}
