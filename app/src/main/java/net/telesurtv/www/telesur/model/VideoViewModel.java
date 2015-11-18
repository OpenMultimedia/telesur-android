package net.telesurtv.www.telesur.model;

/**
 * Created by Jhordan on 27/07/15.
 */
public class VideoViewModel {

    private String background;
    private String duration;
    private String title;
    private String category;
    private String data;
    private String visitCounter;
    private String videoURL;
    private String description;
    private String linkVideoNavegator;
    private String corresponsalName;
    private String corresponsalSlug;
    private String corresponsalContrySlug;
    private String topicSlug;
    private String categorySlug;
    private int primaryColor;

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public String getCorresponsalName() {
        return corresponsalName;
    }

    public void setCorresponsalName(String corresponsalName) {
        this.corresponsalName = corresponsalName;
    }

    public String getCorresponsalSlug() {
        return corresponsalSlug;
    }

    public void setCorresponsalSlug(String corresponsalSlug) {
        this.corresponsalSlug = corresponsalSlug;
    }

    public String getCorresponsalContrySlug() {
        return corresponsalContrySlug;
    }

    public void setCorresponsalContrySlug(String corresponsalContrySlug) {
        this.corresponsalContrySlug = corresponsalContrySlug;
    }

    public String getTopicSlug() {
        return topicSlug;
    }

    public void setTopicSlug(String topicSlug) {
        this.topicSlug = topicSlug;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(String visitCounter) {
        this.visitCounter = visitCounter;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public int getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkVideoNavegator() {
        return linkVideoNavegator;
    }

    public void setLinkVideoNavegator(String linkVideoNavegator) {
        this.linkVideoNavegator = linkVideoNavegator;
    }

}
