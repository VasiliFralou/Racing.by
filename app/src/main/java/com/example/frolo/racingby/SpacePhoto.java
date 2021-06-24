package com.example.frolo.racingby;

import android.os.Parcel;
import android.os.Parcelable;

public class SpacePhoto implements Parcelable {

    private String mUrl;
    private String mTitle;

    public SpacePhoto(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected SpacePhoto(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<SpacePhoto> CREATOR = new Creator<SpacePhoto>() {
        @Override
        public SpacePhoto createFromParcel(Parcel in) {
            return new SpacePhoto(in);
        }

        @Override
        public SpacePhoto[] newArray(int size) {
            return new SpacePhoto[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static  SpacePhoto[] getSpacePhotos() {

        return new SpacePhoto[]{
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e0ed/Le5VLFVyiK4.jpg", "G-Drive"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e115/_Toz6h8zmbc.jpg", "SR20"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e1dd/7olUzWDsExo.jpg", "BMW E92"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e223/08uZXh8hAcA.jpg", "Mazda rx8"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e29b/z219hd8HR4M.jpg", "Gediminas Ivanauskas"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e2b9/_ePQwYEUCrs.jpg", "Toyota Supra"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e2cd/-woAOozd6mk.jpg", "Дмитрий Нагула"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e395/pPFk4AMvGHY.jpg", "AE86"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e38b/jbgrvBKo0pI.jpg", "G-Drive"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e61f/7iGWsrmGu5g.jpg", "No Motors"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e629/7CbXAiGCC1s.jpg", "Nissan"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e65b/PJTtvdqTRmQ.jpg", "Сергей Сак"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e723/dWj1vhdIzws.jpg", "BMW"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e719/e1F5_EOHTsY.jpg", "BMW M3 E92"),
                new SpacePhoto("https://pp.userapi.com/c849028/v849028274/6e827/5QYeVnJr694.jpg", "Награждение"),
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}
