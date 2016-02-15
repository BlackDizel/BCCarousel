package ru.byters.carousel.controllers.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import ru.byters.carousel.controllers.Core;
import ru.byters.carousel.ui.fragments.FragmentFrame;

public class FramesAdapter extends FragmentStatePagerAdapter {

    private static final int NO_VALUE = -1;
    Core controller;
    private int genre_id;

    public FramesAdapter(FragmentManager manager, Core controller) {
        super(manager);
        this.controller = controller;
        genre_id = NO_VALUE;
    }

    public void setGenre(int id) {
        this.genre_id = id;
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        //dirty hack to remove fragments on notifydatachange
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        if (getCount() > 0 && genre_id != NO_VALUE)
            return FragmentFrame.newInstance(genre_id, position);
        return null;
    }

    @Override
    public int getCount() {
        return genre_id == NO_VALUE ? 0 : controller.getControllerFrames().getSize(genre_id);
    }
}
