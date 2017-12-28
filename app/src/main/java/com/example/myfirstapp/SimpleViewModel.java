package com.example.myfirstapp;

import android.support.annotation.NonNull;

/**
 * Created by melissa young on 12/12/17.
 * Part of RecyclerView Tutorial
 * Common object that will hold our data
 */

public class SimpleViewModel {

    private String simpleText;

    public SimpleViewModel(@NonNull final String simpleText) {
        setSimpleText(simpleText);
    }

    @NonNull // <-- Denotes that a param, field, method can NEVER be null
    public String getSimpleText() {
        return simpleText;
    }

    public void setSimpleText(@NonNull final String simpleText) {
        this.simpleText = simpleText;
    }
}
