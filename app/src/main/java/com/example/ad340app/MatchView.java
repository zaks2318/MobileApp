package com.example.ad340app;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MatchView implements Parcelable {
    public boolean liked;
    public String imageUrl;
    public String uid;
    public String name;

    public MatchView(){

    }

    public MatchView(String name, boolean liked, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
        this.liked = liked;
    }

    public MatchView (Parcel in){
        name = in.readString();
        liked = in.readByte() !=0;
        imageUrl = in.readString();
    }

    public static final Creator<MatchView> CREATOR = new Creator<MatchView>() {
        @Override
        public MatchView createFromParcel(Parcel in) {
            return new MatchView(in);
        }

        @Override
        public MatchView[] newArray(int size) {
            return new MatchView[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (liked ? 1 : 0));
        dest.writeString(imageUrl);
    }
}
