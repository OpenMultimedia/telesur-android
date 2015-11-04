package net.telesurtv.www.telesur.data.api.models.review;

/**
 * Created by Jhordan on 27/10/15.
 */
import java.util.HashMap;
import java.util.Map;


public class Reviews {

    private Rss rss;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The rss
     */
    public Rss getRss() {
        return rss;
    }

    /**
     *
     * @param rss
     * The rss
     */
    public void setRss(Rss rss) {
        this.rss = rss;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}