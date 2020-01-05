package com.kuks.illustrator.data.local_repo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.kuks.illustrator.data.RepositoryContract;
import com.kuks.illustrator.data.Asset;
import com.kuks.illustrator.utils.Constants;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AssetsRepository implements RepositoryContract {

    private volatile static AssetsRepository INSTANCE = null;
    private Context mContext;
    private List<Asset> mAssetsList=new ArrayList<>();
    private List<Asset> mDrawableList=new ArrayList<>();

    private AssetsRepository(Context context) {
        mContext=context;
    }

    public static AssetsRepository getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AssetsRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AssetsRepository(context);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * load files from assets folder
     * @param path
     * @return assets list
     */
    @Override
    public List<Asset> loadAssets(String path) {
        createAssetsList(path);
        return mAssetsList;

    }

    /**
     * load files from drawable folder
     * @return assets list
     */
    @Override
    public List<Asset> loadDrawables() {
        return createDrawableList();
    }

    /**
     * Recursively search for files in directories
     * @param path
     * @return
     */
    private boolean createAssetsList(String path) {
        String [] list;
        try {
            list = mContext.getAssets().list(path);
            if (list.length > 0) {
                // This is a folder
                for (String file : list) {
                    if (!createAssetsList(path + "/" + file)) {
                        return false;
                    }
                    else {
                        /**
                         * filter out android framework resources, remove if condition to list android framework resources
                         */
                        if (file.contains("com_kuks") || file.contains("icons8"))
                        {
                            mAssetsList.add(new Asset(file,Constants.DRAWABLE_TYPE.ASSET));
                        }


                    }
                }
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    private List<Asset> createDrawableList(){
        Field[] drawablesFields = com.kuks.illustrator.R.drawable.class.getFields();
        ArrayList<Drawable> drawables = new ArrayList<>();

        for (Field field : drawablesFields) {
            try {

                /**
                 * filter out android framework resources, remove if condition to list android framework resources
                 */
                if (field.getName().contains("com_kuks") || field.getName().contains("icons8"))
                {
                    mDrawableList.add(new Asset(field.getName(),Constants.DRAWABLE_TYPE.DRAWABLE));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mDrawableList;
    }
}
