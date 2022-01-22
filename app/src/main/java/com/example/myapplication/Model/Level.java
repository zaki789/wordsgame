package com.example.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class Level implements Parcelable {

    int id;
    ArrayList<String> words = new ArrayList<>();

    protected Level(Parcel in) {
        id = in.readInt();
        words = in.createStringArrayList();
    }

    public Level() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeStringList(words);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Level> CREATOR = new Creator<Level>() {
        @Override
        public Level createFromParcel(Parcel in) {
            return new Level(in);
        }

        @Override
        public Level[] newArray(int size) {
            return new Level[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }
}
