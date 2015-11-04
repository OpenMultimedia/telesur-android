package net.telesurtv.www.telesur.data.api.models.review;

/**
 * Created by Jhordan on 27/10/15.
 */

import java.util.HashMap;
import java.util.Map;

public class Rss {

    private String Version;
    private Channel channel;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Version
     */
    public String getVersion() {
        return Version;
    }

    /**
     *
     * @param Version
     * The @version
     */
    public void setVersion(String Version) {
        this.Version = Version;
    }

    /**
     *
     * @return
     * The channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     *
     * @param channel
     * The channel
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}