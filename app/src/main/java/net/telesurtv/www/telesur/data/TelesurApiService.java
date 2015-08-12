package net.telesurtv.www.telesur.data;


import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Jhordan on 28/07/15.
 */
public interface TelesurApiService {

    @GET(EndPoint.TELESUR_CLIP)
    Observable<Response> getVideosList(@Query(EndPoint.CLIP_QUERY_DETAIL) String detail, @Query(EndPoint.CLIP_QUERY_FIRST) int first, @Query(EndPoint.CLIP_QUERY_LAST) int last, @Query(EndPoint.CLIP_QUERY_KIND) String category);

    @GET(EndPoint.TELESUR_RSS)
    Observable<Response> getNewsList(@Query(EndPoint.RSS_URL) String rss);

}
