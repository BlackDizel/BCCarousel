package ru.byters.carousel.controllers.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.byters.carousel.controllers.Core;
import ru.byters.carousel.ui.fragments.FragmentGenre;

public class CarouselAdapter extends FragmentPagerAdapter {

    Core controller;

    public CarouselAdapter(FragmentManager manager, Core controller) {
        super(manager);
        this.controller = controller;
    }

    @Override
    public Fragment getItem(int position) {
        if (controller.getControllerGenre().getSize() > 0) {
            position = position % controller.getControllerGenre().getSize(); // use modulo for infinite cycling
            return FragmentGenre.newInstance(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return controller.getControllerGenre().getSize() * 100; // simulate infinite by big number of products
    }
}
