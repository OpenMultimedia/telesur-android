package net.telesurtv.www.telesur.data.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhordan on 28/09/15.
 */
public class Notification {

    @SerializedName("title")
    private String title;

    @SerializedName("userdata")
    private UserData userData;

    public String getTitle() {
        return title;
    }

    public UserData getUserData() {
        return userData;
    }


}
