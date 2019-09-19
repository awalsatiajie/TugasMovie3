package com.awalsatiajie.tugasmovie3.movie;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieItem implements Parcelable {

    private int id;
    private String posterPath, title, overview;

    protected MovieItem(Parcel in) {
        id = in.readInt();
        posterPath = in.readString();
        title = in.readString();
        overview = in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
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

    MovieItem(JSONObject object) {
        try {
            int id = object.getInt("id");
            String posterPath = object.getString("poster_path");
            String title = object.getString("title");
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
