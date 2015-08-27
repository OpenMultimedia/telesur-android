package net.telesurtv.www.telesur.data;

/**
 * Created by Jhordan on 28/07/15.
 */
public class EndPoint {

    public final static String TELESUR_API = "http://multimedia.telesurtv.net";
    public final static String TELESUR_RSS = "/telesur-rss";
    public final static String RSS_URL = "url";
    ;
    public final static String TELESUR_CLIP = "/api/clip/";
    public final static String CLIP_QUERY_DETAIL = "detalle";
    public final static String CLIP_QUERY_FIRST = "primero";
    public final static String CLIP_QUERY_LAST = "ultimo";
    public final static String CLIP_QUERY_KIND = "tipo";
    public final static String CLIP_QUERY_PROGRAM = "programa";
    public final static String TELESUR_PROGRAM = "/api/programa/";
    public final static String PROGRAM_QUERY_SHOW = "mostrar";
    public final static String PROGRAM_QUERY_LAST = "ultimo";

    public static final String RSS_OUSTANDING = "/rss/RssPortada.xml";
    public static final String RSS_LATAM = "/rss/RssLatinoamerica.xml";
    public static final String RSS_WORLD = "/rss/RssMundo.xml";
    public static final String RSS_SPORTS = "/rss/RssDeporte.xml";
    public static final String RSS_CULTURE = "/rss/RssCultura.xml";

    public static final String SECTION_OUSTANDING = "Destacado";
    public static final String SECTION_LATAM = "América Latina";
    public static final String SECTION_WORLD = "Mundo";
    public static final String SECTION_SPORT = "Deportes";
    public static final String SECTION_CULTURE = "Cultura";


    public static final int REQUEST_SUCCES = 200;
    public static final String UTF_8 = "UTF-8";


}
