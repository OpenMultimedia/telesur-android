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
    Observable<Response> getVideoList(@Query(EndPoint.CLIP_QUERY_DETAIL) String detail, @Query(EndPoint.CLIP_QUERY_FIRST) int first, @Query(EndPoint.CLIP_QUERY_LAST) int last, @Query(EndPoint.CLIP_QUERY_KIND) String category);

    @GET(EndPoint.TELESUR_RSS)
    Observable<Response> getNewsList(@Query(EndPoint.RSS_URL) String rss);

    @GET(EndPoint.TELESUR_PROGRAM)
    Observable<Response> getProgramList(@Query(EndPoint.PROGRAM_QUERY_LAST)String last ,@Query(EndPoint.PROGRAM_QUERY_SHOW)String name,@Query(EndPoint.PROGRAM_QUERY_SHOW)String slug);

    @GET(EndPoint.TELESUR_CLIP)
    Observable<Response> getPrograms(@Query(EndPoint.CLIP_QUERY_DETAIL) String detail, @Query(EndPoint.CLIP_QUERY_FIRST) int first, @Query(EndPoint.CLIP_QUERY_LAST) int last, @Query(EndPoint.CLIP_QUERY_KIND) String category,@Query((EndPoint.CLIP_QUERY_PROGRAM)) String program);


}
