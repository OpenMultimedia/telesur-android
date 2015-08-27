package net.telesurtv.www.telesur.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jhordan on 15/07/15.
 */
public class Config {


    public static final String NEWS_TITLE = "news_title";
    public static final String NEWS_DESCRIPTION = "news_description";
    public static final String NEWS_AUTHOR = "news_author";
    public static final String NEWS_CONTENT = "news_content";
    public static final String NEWS_CATEGORY = "news_category";
    public static final String NEWS_IMAGE = "news_image";
    public static final String NEWS_DATE = "news_date";
    public static final String NEWS_LINK= "news_link";


    public static String date_to_human(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        Date date;
        try {
            date = df.parse(time);
            SimpleDateFormat post = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
            time = post.format(date);
        } catch (Exception e) {
        }

        return time;

    }

    public static String getDateFormated(String myTime) {
        String finalDate = "";
        // String myTime = "Mon, 13 Aug 2015 13:42:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss", Locale.US);
        Date date = null;
        try {
            date = dateFormat.parse(myTime);
            SimpleDateFormat postFormat = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
            // SimpleDateFormat postFormat = new SimpleDateFormat("EEE MMM yyyy-MM-dd hh:mm:ss");
            finalDate = postFormat.format(date);

        } catch (Exception e) {
        }

        return finalDate;


    }

    public static Date parserToUseTimeAgo(String myTime) {

        String finalDate = "";
        Date dateTimeAgo = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss", Locale.US);
            finalDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).format(dateFormat.parse(myTime));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dateTimeAgo = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(finalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateTimeAgo;

    }


}
