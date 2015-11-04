package net.telesurtv.www.telesur.data;


import net.telesurtv.www.telesur.data.api.models.program.Program;
import net.telesurtv.www.telesur.data.api.models.review.Reviews;
import net.telesurtv.www.telesur.data.api.models.streaming.RootSchedule;
import net.telesurtv.www.telesur.data.api.models.video.Videos;

import java.util.ArrayList;
import java.util.List;

import retrofit.Response;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Jhordan on 28/07/15.
 */
public interface TelesurApiService {

    @GET(TelesurApiConstants.TELESUR_CLIP)
    Observable<ArrayList<Videos>> getVideoList(@Query(TelesurApiConstants.CLIP_QUERY_DETAIL) String detail, @Query(TelesurApiConstants.CLIP_QUERY_FIRST) int first, @Query(TelesurApiConstants.CLIP_QUERY_LAST) int last, @Query(TelesurApiConstants.CLIP_QUERY_KIND) String category);

    @GET(TelesurApiConstants.TELESUR_RSS)
    Observable<Reviews> getNewsList(@Query(TelesurApiConstants.RSS_URL) String rss);

    @GET(TelesurApiConstants.TELESUR_PROGRAM)
    Observable<ArrayList<Program>> getProgramList(@Query(TelesurApiConstants.PROGRAM_QUERY_LAST)String last ,@Query(TelesurApiConstants.PROGRAM_QUERY_SHOW)String name,@Query(TelesurApiConstants.PROGRAM_QUERY_SHOW)String slug);

    @GET(TelesurApiConstants.TELESUR_CLIP)
    Observable<Response> getPrograms(@Query(TelesurApiConstants.CLIP_QUERY_DETAIL) String detail, @Query(TelesurApiConstants.CLIP_QUERY_FIRST) int first, @Query(TelesurApiConstants.CLIP_QUERY_LAST) int last, @Query(TelesurApiConstants.CLIP_QUERY_KIND) String category,@Query((TelesurApiConstants.CLIP_QUERY_PROGRAM)) String program);

    @GET(TelesurApiConstants.TELESUR_CLIP)
    Observable<Response> getAllPrograms(@Query(TelesurApiConstants.CLIP_QUERY_DETAIL) String detail, @Query(TelesurApiConstants.CLIP_QUERY_FIRST) int first, @Query(TelesurApiConstants.CLIP_QUERY_LAST) int last, @Query(TelesurApiConstants.CLIP_QUERY_KIND) String category);

    @GET(TelesurApiConstants.TELESUR_SCHEDULE)
    Observable <RootSchedule> getSchedule();



}
