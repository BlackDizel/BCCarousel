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
import ru.byters.carousel.models.Frame;

public class FragmentFrame
        extends Fragment {

    private static final String INTENT_ITEM_POSITION = "item_position";
    private static final String INTENT_GENRE = "genre";

    public static Fragment newInstance(int genre_id, int position) {
        Bundle args = new Bundle();
        args.putInt(INTENT_ITEM_POSITION, position);
        args.putInt(INTENT_GENRE, genre_id);
        Fragment f = new FragmentFrame();
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frame, container, false);

        int pos = getArguments().getInt(INTENT_ITEM_POSITION);
        int genre = getArguments().getInt(INTENT_GENRE);

        Frame item = ((Core) getContext().getApplicationContext()).getControllerFrames().getItem(genre, pos);
        if (item != null) {
            ((TextView) v.findViewById(R.id.tvTitle)).setText(item.getTitle());
            ImageView imgView = (ImageView) v.findViewById(R.id.ivFrame);

            Picasso.with(getContext())
                    .load(item.getImgUri())
                    .into(imgView);
        }

        return v;
    }
}
