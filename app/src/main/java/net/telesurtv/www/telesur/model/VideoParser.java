package net.telesurtv.www.telesur.model;

import net.telesurtv.www.telesur.data.api.models.Estadistics;

import java.util.List;

/**
 * Created by Jhordan on 29/07/15.
 */
public class VideoParser {

    private String city;
    private String description;
    private List<Estadistics> estadisticsList;
    private String urlFileVideo;
    private String data;
    private String title;
    private String urlBackgroundImage;
    private String urldownloadVideo;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Estadistics> getEstadisticsList() {
        return estadisticsList;
    }

    public void setEstadisticsList(List<Estadistics> estadisticsList) {
        this.estadisticsList = estadisticsList;
    }

    public String getUrlFileVideo() {
        return urlFileVideo;
    }

    public void setUrlFileVideo(String urlFileVideo) {
        this.urlFileVideo = urlFileVideo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlBackgroundImage() {
        return urlBackgroundImage;
    }

    public void setUrlBackgroundImage(String urlBackgroundImage) {
        this.urlBackgroundImage = urlBackgroundImage;
    }

    public String getUrldownloadVideo() {
        return urldownloadVideo;
    }

    public void setUrldownloadVideo(String urldownloadVideo) {
        this.urldownloadVideo = urldownloadVideo;
    }




}
