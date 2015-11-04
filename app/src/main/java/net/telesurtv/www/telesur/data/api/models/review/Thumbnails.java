package net.telesurtv.www.telesur.data.api.models.review;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jhordan on 27/10/15.
 */
public class Thumbnails {

    private String Type;
    private String Url;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type The @type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return The Url
     */
    public String getUrl() {
        return Url;
    }

    /**
     * @param Url The @url
     */
    public void setUrl(String Url) {
        this.Url = Url;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
