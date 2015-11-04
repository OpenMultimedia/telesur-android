package net.telesurtv.www.telesur.data.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhordan on 28/09/15.
 */
public class UserData {

    @SerializedName("link")
    private String link;

    @SerializedName("section")
    private String section;

    public String getSection() {
        return section;
    }

    public String getLink() {
        return link;
    }

}
