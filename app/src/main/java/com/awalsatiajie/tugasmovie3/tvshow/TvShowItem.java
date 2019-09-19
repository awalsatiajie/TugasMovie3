package com.awalsatiajie.tugasmovie3.tvshow;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvShowItem implements Parcelable {

    private int id;
    private String posterPath, title, overview;

    protected TvShowItem(Parcel in) {
        id = in.readInt();
        posterPath = in.readString();
        title = in.readString();
        overview = in.readString();
    }

    public static final Creator<TvShowItem> CREATOR = new Creator<TvShowItem>() {
        @Override
        public TvShowItem createFromParcel(Parcel in) {
            return new TvShowItem(in);
        }

        @Override
        public TvShowItem[] newArray(int size) {
            return new TvShowItem[size];
        }
    };

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getPosterPath(){
        String sbackdropPath = "https://image.tmdb.org/t/p/w342" + posterPath;
        return sbackdropPath;
    }

    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getOverview(){
        return overview;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    TvShowItem(JSONObject object) {
        try {
            int id = object.getInt("id");
            String posterPath = object.getString("poster_path");
            String title = object.getString("name");
            String overview = object.getString("overview");

            this.id = id;
            this.posterPath = posterPath;
            this.title = title;
            this.overview = overview;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
    }
}
