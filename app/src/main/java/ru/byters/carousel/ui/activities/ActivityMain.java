package ru.byters.carousel.ui.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;

import ru.byters.carousel.R;
import ru.byters.carousel.controllers.Core;
import ru.byters.carousel.controllers.adapters.CarouselAdapter;
import ru.byters.carousel.controllers.adapters.FramesAdapter;
import ru.byters.carousel.models.Genre;
import ru.byters.carousel.ui.activities.utils.ZoomTransformer;

public class ActivityMain
        extends ActivityBase
        implements ViewPager.OnPageChangeListener {

    private FramesAdapter framesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPagerFrames();
        initViewPagerGenre();
    }

    private void initViewPagerFrames() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.vpFrames);
        framesAdapter = new FramesAdapter(getSupportFragmentManager(), (Core) getApplicationContext());
        viewPager.setAdapter(framesAdapter);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int cell = (int) (width / (float) 3);
        int padding = (width - cell) / 2;
        viewPager.setPadding(padding, viewPager.getPaddingTop(), padding, viewPager.getPaddingBottom());
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.frame_item_margin_horizontal));
    }

    private void initViewPagerGenre() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.vpGenre);

        int frames_num = getResources().getInteger(R.integer.genre_frames_num);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels - 2 * getResources().getDimensionPixelSize(R.dimen.genre_margin_horizontal);
        int cell = (int) (width / (float) frames_num);
        int padding = (width - cell) / 2;

        viewPager.setClipToPadding(false);
        viewPager.setPadding(padding, viewPager.getPaddingTop(), padding, viewPager.getPaddingBottom());

        CarouselAdapter adapter = new CarouselAdapter(getSupportFragmentManager(), (Core) getApplicationContext());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(adapter.getCount() / 2);
        onPageSelected(adapter.getCount() / 2);

        viewPager.setPageTransformer(true, new ZoomTransformer(frames_num));
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int realpos = position %= ((Core) getApplicationContext()).getControllerGenre().getSize();
        Genre genre = ((Core) getApplicationContext()).getControllerGenre().getItem(realpos);
        if (genre == null) return;
        framesAdapter.setGenre(genre.getId());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
