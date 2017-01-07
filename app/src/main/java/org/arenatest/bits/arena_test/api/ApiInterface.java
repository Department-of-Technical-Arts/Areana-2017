package org.arenatest.bits.arena_test.api;

import org.arenatest.bits.arena_test.sets.EventsData;
import org.arenatest.bits.arena_test.sets.MedalData;
import org.arenatest.bits.arena_test.sets.ScheduleData;
import org.arenatest.bits.arena_test.sets.SponsorData;
import org.arenatest.bits.arena_test.sets.TokenItem;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tejeshwar on 19/12/16.
 */

public interface ApiInterface {

    @GET("api/")
    Call<ScheduleData> getSchedule(@Query("tag") String tag, @Query("updated_at") long updated_at);

    @GET("api/")
    Call<EventsData> getEvents(@Query("tag") String tag, @Query("updated_at") long updated_at );

    @GET("api/")
    Call<MedalData> getResults(@Query("tag") String tag, @Query("updated_at") long updated_at);

    @GET("api/")
    Call<TokenItem> addToken(@Query("tag") String tag, @Query("token") String token);

    @GET("api/")
    Call<SponsorData> getSponsors(@Query("tag") String tag, @Query("updated_at") long updated_at);
}
