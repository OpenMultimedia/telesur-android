package net.telesurtv.www.telesur.data.api.models.review;

/**
 * Created by Jhordan on 27/10/15.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Channel {

    private Object category;
    private List<Item> item = new ArrayList<Item>();
    private String link;
    private String description;
    private String title;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The category
     */
    public Object getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(Object category) {
        this.category = category;
    }

    /**
     * @return The item
     */
    public List<Item> getItem() {
        return item;
    }

    /**
     * @param item The item
     */
    public void setItem(List<Item> item) {
        this.item = item;
    }

    /**
     * @return The link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}