package com.kuks.illustrator.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityUtils {

    public static void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment, String tag,int containerID) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(containerID,fragment, tag);
        transaction.commit();
    }


}
