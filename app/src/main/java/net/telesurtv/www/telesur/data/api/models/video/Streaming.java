package net.telesurtv.www.telesur.data.api.models.video;

/**
 * Created by Jhordan on 29/07/15.
 */
public class Streaming {

    private String rtsp_url;
    private String rtmp_server;
    private String rtmp_file;

    public String getRtsp_url() {
        return rtsp_url;
    }

    public String getRtmp_server() {
        return rtmp_server;
    }

    public String getRtmp_file() {
        return rtmp_file;
    }
}
