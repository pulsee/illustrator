package com.kuks.illustrator.views;

import android.os.Bundle;

import com.kuks.illustrator.Event;
import com.kuks.illustrator.R;
import com.kuks.illustrator.data.Asset;
import com.kuks.illustrator.utils.ActivityUtils;
import com.kuks.illustrator.viewmodels.AssetsListViewModel;
import com.kuks.illustrator.viewmodels.ViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private AssetsListViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setupViewFragment();
        mViewModel=getViewModel(this);

        mViewModel.getAssetClickEvent().observe(this, new Observer<Event<Asset>>() {
            @Override
            public void onChanged(Event<Asset> assetEvent) {
                ImageViewFragment.showDialog(getSupportFragmentManager(),"imageDialog",assetEvent.getContentIfNotHandled());
            }
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupViewFragment() {
        AssetsListFragment assetsListFragment = (AssetsListFragment) getSupportFragmentManager().findFragmentById(R.id.frame);
        if (assetsListFragment == null) {
            // Create the fragment
            assetsListFragment = AssetsListFragment.newInstance();
            ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), assetsListFragment, AssetsListFragment.TAG, R.id.frame);
        }
    }

    public static AssetsListViewModel getViewModel(FragmentActivity activity) {

        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        AssetsListViewModel viewModel = ViewModelProviders.of(activity,factory).get(AssetsListViewModel.class);

        return viewModel;
    }
}
