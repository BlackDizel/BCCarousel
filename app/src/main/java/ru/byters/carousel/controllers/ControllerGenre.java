package ru.byters.carousel.controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ru.byters.carousel.controllers.utils.JsonResourcesHelper;
import ru.byters.carousel.models.Genre;

public class ControllerGenre {
    @NonNull
    Core controller;

    @Nullable
    ArrayList<Genre> data;

    public ControllerGenre(@NonNull Core core) {
        this.controller = core;

        Type listType = new TypeToken<ArrayList<Genre>>() {
        }.getType();
        data = new Gson().fromJson(JsonResourcesHelper.getJsonString(core, JsonResourcesHelper.GENRES), listType);
    }

    public int getSize() {
        return data == null ? 0 : data.size();
    }

    @Nullable
    public Genre getItem(int pos) {
        if (data == null) return null;
        if (pos < 0 || pos >= data.size()) return null;
        return data.get(pos);
    }
}
