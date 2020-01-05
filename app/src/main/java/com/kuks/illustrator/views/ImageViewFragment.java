package com.kuks.illustrator.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kuks.illustrator.data.Asset;
import com.kuks.illustrator.databinding.FragmentAssetListBinding;
import com.kuks.illustrator.databinding.FragmentImageBinding;
import com.kuks.illustrator.utils.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

public class ImageViewFragment extends BottomSheetDialogFragment {

    private FragmentImageBinding mFragImageBinding;
    private Asset mAsset;

    public static ImageViewFragment newInstance(Asset asset)
    {
        ImageViewFragment fragment=new ImageViewFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable(Constants.KEY_ASSET,asset);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!=null && getArguments().getParcelable(Constants.KEY_ASSET)!=null)
        {
            mAsset= getArguments().getParcelable(Constants.KEY_ASSET);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragImageBinding =FragmentImageBinding.inflate(inflater, container, false);

        mFragImageBinding.setAsset(mAsset);

        return mFragImageBinding.getRoot();
    }

    public static void showDialog(FragmentManager fm, String tag, Asset asset) {
        if (fm.findFragmentByTag(tag) == null) {
            fm.beginTransaction().add(newInstance(asset), tag).commitAllowingStateLoss();
        }

    }
}
