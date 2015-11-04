package net.telesurtv.www.telesur.data.api.models.review;

/**
 * Created by Jhordan on 27/10/15.
 */
import java.util.HashMap;
import java.util.Map;



public class Item {

    private String category;
    private String description;
    private String pubDate;
    private String title;
    private String author;
    private String content;
    private String link;
    private String lastTime;
    private Enclosure enclosure;
    private Thumbnails thumbnails;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     *
     * @param pubDate
     * The pubDate
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     * The content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     * The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The lastTime
     */
    public String getLastTime() {
        return lastTime;
    }

    /**
     *
     * @param lastTime
     * The lastTime
     */
    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    /**
     *
     * @return
     * The enclosure
     */
    public Enclosure getEnclosure() {
        return enclosure;
    }

    /**
     *
     * @param enclosure
     * The enclosure
     */
    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    /**
     *
     * @return
     * The thumbnails
     */
    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    /**
     *
     * @param thumbnails
     * The thumbnails
     */
    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}