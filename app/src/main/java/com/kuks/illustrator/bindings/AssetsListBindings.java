package com.kuks.illustrator.bindings;

import com.kuks.illustrator.data.Asset;
import com.kuks.illustrator.views.AssetsRecyclerviewAdapter;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * bind "items" attribute in xml layout with corresponding method in class
 */
public class AssetsListBindings {

    @BindingAdapter("items")
    public static void setItems(RecyclerView recyclerView, List<Asset> items) {
        AssetsRecyclerviewAdapter adapter = (AssetsRecyclerviewAdapter) recyclerView.getAdapter();
        if (adapter != null)
        {
            adapter.setList(items);
        }
    }

}
