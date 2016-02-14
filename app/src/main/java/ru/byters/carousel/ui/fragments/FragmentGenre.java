package ru.byters.carousel.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.byters.carousel.R;
import ru.byters.carousel.controllers.Core;
import ru.byters.carousel.models.Genre;
import ru.byters.carousel.ui.activities.utils.ZoomTransformer;

public class FragmentGenre
        extends Fragment {

    private static final String INTENT_ITEM_POSITION = "item_position";

    public static Fragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(INTENT_ITEM_POSITION, position);
        Fragment f = new FragmentGenre();
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_genre, container, false);

        int pos = getArguments().getInt(INTENT_ITEM_POSITION);

        Genre item = ((Core) getContext().getApplicationContext()).getControllerGenre().getItem(pos);
        if (item != null) {
            ((TextView) v.findViewById(R.id.tvTitle)).setText(item.getTitle());
            ImageView imgView = (ImageView) v.findViewById(R.id.ivGenre);

            Picasso.with(getContext())
                    .load(item.getImgUri())
                    .into(imgView);
        }

        v.setScaleX(ZoomTransformer.MIN_SCALE);
        v.setScaleY(ZoomTransformer.MIN_SCALE);

        return v;
    }
}
