package net.telesurtv.www.telesur.data.api.models.news;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jhordan on 03/08/15.
 */
public class News {

    @SerializedName("category")
    private String category;
    @SerializedName("description")
    private String description;
    @SerializedName("pubDate")
    private String pubDate;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("content")
    private String content;
    @SerializedName("link")
    private String link;
    @SerializedName("lastTime")
    private String lastTime;
    @SerializedName("enclosure")
    private Enclosure enclosure;
    @SerializedName("thumbnails")
    private Thumbnails thumbnails;

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public String getLastTime() {
        return lastTime;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }
}
