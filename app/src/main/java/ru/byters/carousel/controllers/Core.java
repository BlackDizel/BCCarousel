package ru.byters.carousel.controllers;

import android.app.Application;

public class Core extends Application {

    private ControllerGenre controllerGenre;
    private ControllerFrames controllerFrames;

    @Override
    public void onCreate() {
        super.onCreate();

        controllerGenre = new ControllerGenre(this);
        controllerFrames = new ControllerFrames(this);


    }

    public ControllerFrames getControllerFrames() {
        return controllerFrames;
    }

    public ControllerGenre getControllerGenre() {
        return controllerGenre;
    }
}
