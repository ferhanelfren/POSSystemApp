package com.example.pos;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public FragmentAdapter(FragmentManager fragmentmanager, Lifecycle lifecycle) {
        super(fragmentmanager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 1:
                return new CategoryFragmentSecondPage();


        }

        return new CategoryFragmentFirstPage();



    }

    @Override
    public int getItemCount() {
        //number of Blank Fragments nimo
        return 2;
    }
}
