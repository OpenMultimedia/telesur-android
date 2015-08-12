package net.telesurtv.www.telesur.data.api.models.news;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhordan on 03/08/15.
 */
public class Enclosure {

    @SerializedName("#text")
    private String text;
    @SerializedName("@type")
    private String type;
    @SerializedName("@url")
    private String url;

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

}
