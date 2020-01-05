package com.kuks.illustrator.data;

import android.content.Context;

import com.kuks.illustrator.data.local_repo.AssetsRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class RepositoryImpl implements RepositoryContract {

    private volatile static RepositoryImpl INSTANCE = null;
    private final Context mContext;

    private final MutableLiveData<Asset> mAssetLiveData = new MutableLiveData<>();

    private RepositoryImpl(Context context) {
        mContext=context;
    }

    public static RepositoryImpl getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RepositoryImpl(context);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public List<Asset> loadAssets(String path) {
        return AssetsRepository.getInstance(mContext).loadAssets(path);
    }

    @Override
    public List<Asset> loadDrawables() {
        return AssetsRepository.getInstance(mContext).loadDrawables();
    }
}
