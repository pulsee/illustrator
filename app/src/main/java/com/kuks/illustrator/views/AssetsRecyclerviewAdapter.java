package com.kuks.illustrator.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kuks.illustrator.R;
import com.kuks.illustrator.databinding.RowAssetBinding;
import com.kuks.illustrator.data.Asset;
import com.kuks.illustrator.viewmodels.AssetsListViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class AssetsRecyclerviewAdapter extends RecyclerView.Adapter<AssetsRecyclerviewAdapter.AssetViewHolder> {
    private final AssetsListViewModel mAssetListViewModel;
    private final LifecycleOwner mLifecycleOwner;
    private List<Asset> mAssets;

    public AssetsRecyclerviewAdapter(ArrayList<Asset> assets, AssetsListViewModel assetListViewModel, FragmentActivity activity) {
        mAssetListViewModel=assetListViewModel;
        setList(assets);
        mLifecycleOwner = activity;

    }

    @NonNull
    @Override
    public AssetsRecyclerviewAdapter.AssetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowAssetBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_asset, parent, false);

        return new AssetViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetsRecyclerviewAdapter.AssetViewHolder holder, int position) {
        holder.rowAssetBinding.setAsset(mAssets.get(position));
        holder.rowAssetBinding.setOnAssetClickListener(mAssetListViewModel);
        holder.rowAssetBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mAssets != null ? mAssets.size() : 0;
    }

    public void setList(List<Asset> items) {
        mAssets = items;
        notifyDataSetChanged();
    }

    public class AssetViewHolder extends RecyclerView.ViewHolder{

        public final RowAssetBinding rowAssetBinding;

        public AssetViewHolder(RowAssetBinding rowAssetBinding) {
            super(rowAssetBinding.getRoot());
            this.rowAssetBinding=rowAssetBinding;
        }
    }
}
