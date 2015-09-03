package net.telesurtv.www.telesur.model;

/**
 * Created by Jhordan on 19/08/15.
 */
public class ProgramViewModel {

    private String image;
    private String title;
    private String category;
    private String programImage;
    private String data;
    private String description;
    private String duration;
    private String linkNavegation;

    public String getLinkNavegation() {
        return linkNavegation;
    }

    public void setLinkNavegation(String linkNavegation) {
        this.linkNavegation = linkNavegation;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public String getProgramImage() {
        return programImage;
    }

    public void setProgramImage(String programImage) {
        this.programImage = programImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
