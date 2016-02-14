package ru.byters.carousel.controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ru.byters.carousel.controllers.utils.JsonResourcesHelper;
import ru.byters.carousel.models.Frame;

public class ControllerFrames {
    @NonNull
    Core controller;

    @Nullable
    ArrayList<Frame> data;

    private int cached_genre_id;
    private ArrayList<Frame> cached_data;

    public ControllerFrames(@NonNull Core core) {
        this.controller = core;

        Type listType = new TypeToken<ArrayList<Frame>>() {
        }.getType();
        data = new Gson().fromJson(JsonResourcesHelper.getJsonString(core, JsonResourcesHelper.FRAMES), listType);
    }

    public int getSize(int genre_id) {
        if (cached_data != null && cached_genre_id == genre_id)
            return cached_data.size();

        if (data == null) return 0;

        cached_genre_id = genre_id;
        cached_data = new ArrayList<>();
        for (Frame f : data)
            if (f.getGenre_id() == genre_id)
                cached_data.add(f);

        return cached_data.size();
    }

    @Nullable
    public Frame getItem(int genre_id, int pos) {
        if (genre_id != cached_genre_id)
            getSize(genre_id);

        if (cached_data == null) return null;
        if (pos < 0 || pos >= cached_data.size()) return null;
        return cached_data.get(pos);
    }
}
