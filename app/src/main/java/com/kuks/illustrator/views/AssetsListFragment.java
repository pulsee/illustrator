package com.kuks.illustrator.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuks.illustrator.databinding.FragmentAssetListBinding;
import com.kuks.illustrator.data.Asset;
import com.kuks.illustrator.viewmodels.AssetsListViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AssetsListFragment extends Fragment {

    public static final String TAG="assetListFrag";
    private FragmentAssetListBinding mFragAssetListBinding;
    private AssetsListViewModel mAssetListViewModel;
    private AssetsRecyclerviewAdapter mListAdapter;

    public AssetsListFragment()
    {

    }

    public static AssetsListFragment newInstance() {
        return new AssetsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragAssetListBinding =FragmentAssetListBinding.inflate(inflater, container, false);

        /**
         * sharing viewmodel between fragment and activity
         */
        mAssetListViewModel = MainActivity.getViewModel(getActivity());

        /**
         * bind viewmodel with layout xml
         */
        mFragAssetListBinding.setAssetsListViewModel(mAssetListViewModel);

        /**
         * observe livedata bound to lifecycle of activity
         */
        mFragAssetListBinding.setLifecycleOwner(getActivity());

        return mFragAssetListBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupListAdapter();
        mAssetListViewModel.loadAssets();
        //mAssetListViewModel.loadDrawables();
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    private void setupListAdapter() {
        RecyclerView recyclerView =  mFragAssetListBinding.rVAssets;

        mListAdapter = new AssetsRecyclerviewAdapter(
                new ArrayList<Asset>(2),
                mAssetListViewModel,
                getActivity()
        );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(mListAdapter);
    }
}
