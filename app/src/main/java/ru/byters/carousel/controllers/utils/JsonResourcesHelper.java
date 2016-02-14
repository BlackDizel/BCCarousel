package ru.byters.carousel.controllers.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import ru.byters.carousel.R;

public class JsonResourcesHelper {
    public static final int GENRES= R.raw.genres;
    public static final int FRAMES= R.raw.frames;

    public static String getJsonString(Context context, int resId) {
        InputStream is = context.getResources().openRawResource(resId);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return writer.toString();
    }

}
