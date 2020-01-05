package com.kuks.illustrator.viewmodels;

import android.app.Application;

import com.kuks.illustrator.data.RepositoryImpl;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final RepositoryImpl mRepositoryImpl;
    private final Application mContext;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(RepositoryImpl.getInstance(application),application);
                }
            }
        }
        return INSTANCE;
    }

    public RepositoryImpl getRepositoryImpl() {
        return mRepositoryImpl;
    }


    private ViewModelFactory(RepositoryImpl repository, Application application) {
        mRepositoryImpl = repository;
        mContext=application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AssetsListViewModel.class)) {
            return (T) new AssetsListViewModel(mContext,mRepositoryImpl);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
