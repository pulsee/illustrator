package com.kuks.illustrator.viewmodels;

import android.app.Application;
import android.util.Log;

import com.kuks.illustrator.Event;
import com.kuks.illustrator.data.RepositoryImpl;
import com.kuks.illustrator.data.Asset;
import com.kuks.illustrator.interfaces.OnAssetClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class AssetsListViewModel extends AndroidViewModel implements OnAssetClickListener {


    private static final String TAG = AssetsListViewModel.class.getSimpleName();
    private final RepositoryImpl mRepository;
    private final MutableLiveData<List<Asset>> assetLiveData = new MutableLiveData<>();
    private final MutableLiveData<Event<Asset>> mAssetClickEvent = new MutableLiveData<>();

    public AssetsListViewModel(@NonNull Application application, RepositoryImpl repository) {
        super(application);
        mRepository = repository;
    }

    /**
     * expose livedata to layout xml
     *
     * @return livedata
     */
    public MutableLiveData<List<Asset>> getAssetLiveData() {
        return assetLiveData;
    }

    public void loadAssets() {

        List<Asset> list = new ArrayList<>();

        list.addAll(mRepository.loadAssets(""));
        list.addAll(mRepository.loadDrawables());
        assetLiveData.setValue(list);
    }


    public MutableLiveData<Event<Asset>> getAssetClickEvent() {
        return mAssetClickEvent;
    }

    @Override
    public void onAssetClick(Asset asset) {
        Log.i(TAG, asset.getName() + " clicked");
        mAssetClickEvent.setValue(new Event<>(asset));
    }
}
