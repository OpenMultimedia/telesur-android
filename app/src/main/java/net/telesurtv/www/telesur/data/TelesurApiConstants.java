package net.telesurtv.www.telesur.data;

/**
 * Created by Jhordan on 28/07/15.
 */
public class TelesurApiConstants {

    public final static String TELESUR_API = "http://multimedia.telesurtv.net";
    public final static String TELESUR_RSS = "/telesur-rss";
    public final static String TELESUR_STATIC = "http://static.telesurtv.net";
    public final static String TELESUR_SCHEDULE = "/xml/grilla/tvweb_esp.xml";
    public final static String TELESUR_CLIP = "/api/clip/";
    public final static String TELESUR_SCHEDULE_PHOTOS = "http://www.telesurtv.net/arte/grilla/banners/";
    public final static String RSS_URL = "url";
    public final static String CLIP_QUERY_DETAIL = "detalle";
    public final static String CLIP_QUERY_FIRST = "primero";
    public final static String CLIP_QUERY_LAST = "ultimo";
    public final static String CLIP_QUERY_KIND = "tipo";
    public final static String CLIP_QUERY_PROGRAM = "programa";
    public final static String TELESUR_PROGRAM = "/api/programa/";
    public final static String PROGRAM_QUERY_SHOW = "mostrar";
    public final static String PROGRAM_QUERY_LAST = "ultimo";
    // ?cache=false is a hack to evite server cache and update de RSS
    public static final String RSS_OUSTANDING = "/rss/RssPortada.xml?cache=false";
    public static final String RSS_LATAM = "/rss/RssLatinoamerica.xml?cache=false";
    public static final String RSS_WORLD = "/rss/RssMundo.xml?cache=false";
    public static final String RSS_SPORTS = "/rss/RssDeporte.xml?cache=false";
    public static final String RSS_CULTURE = "/rss/RssCultura.xml?cache=false";

    public static final String RSS_ARTICLE = "/rss/RssOpinion.xml?cache=false";
    public static final String RSS_BLOG = "/rss/RssBlogs.xml?cache=false";
    public static final String RSS_INTERVIEW= "/rss/RssInterviews.xml?cache=false";

    public static final String SECTION_OUSTANDING = "Destacado";
    public static final String SECTION_LATAM = "América Latina";
    public static final String SECTION_WORLD = "Mundo";
    public static final String SECTION_SPORT = "Deportes";
    public static final String SECTION_CULTURE = "Cultura";

    public static final String SECTION_ARTICLE = "Artículo";
    public static final String SECTION_BLOG = "Blog";
    public static final String SECTION_INTERVIEW= "Entrevista";

    public static final String SECTION_VIDEO_INFOGRAFIA = "infografia";
    public static final String SECTION_VIDEO_INTERVIEW = "entrevista";
    public static final String SECTION_VIDEO_SPECIAL = "especial-web";
    public static final String SECTION_VIDEO_NOTICIA = "noticia";
    public static final String SECTION_VIDEO_DOCUMENTAL = "documental";
    public static final String SECTION_VIDEO_REPORT = "reportaje";

    public final static String THEME_OUSTANDING = "news";
    public final static String THEME_LATAM = "interview";
    public final static String THEME_WORLD = "documental";
    public final static String THEME_SPORT = "infografi";
    public final static String THEME_CULTURE = "special";



    public static final int REQUEST_SUCCES = 200;
    public static final String UTF_8 = "UTF-8";


}
