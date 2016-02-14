package ru.byters.carousel.models;

public class Frame {
    private String title;
    private String imgUri;
    private int id;
    private int genre_id;

    public Frame() {
    }

    public String getTitle() {
        return title;
    }

    public String getImgUri() {
        return imgUri;
    }

    public int getId() {
        return id;
    }

    public int getGenre_id() {
        return genre_id;
    }
}
