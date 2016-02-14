package ru.byters.carousel.ui.activities.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ZoomTransformer implements ViewPager.PageTransformer {
    public static final float MIN_SCALE = 0.7f;

    private float centerPosition;

    public ZoomTransformer(int count) {
        this.centerPosition = (count - 1) / 2f;
    }

    public void transformPage(View view, float position) {

        if (centerPosition - 0.5f < position && position < centerPosition + 0.5f) {
            float absPos = position - centerPosition;
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(absPos));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else {
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
            // view.setTranslationY(100f);

        }
    }
}