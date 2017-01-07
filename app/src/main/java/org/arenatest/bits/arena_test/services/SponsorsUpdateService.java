package org.arenatest.bits.arena_test.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import org.arenatest.bits.arena_test.api.ApiClient;
import org.arenatest.bits.arena_test.api.ApiInterface;
import org.arenatest.bits.arena_test.app.Constants;
import org.arenatest.bits.arena_test.database.DBHelper;
import org.arenatest.bits.arena_test.sets.SponsorData;
import org.arenatest.bits.arena_test.sets.SponsorSet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tejeshwar on 6/1/17.
 */

public class SponsorsUpdateService extends IntentService {

    final String TAG = "SponsorsUpdateService";
    public SponsorsUpdateService() {
        super("SponsorsUpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<SponsorData> sponsorDataCall = apiService.getSponsors(Constants.SPONSORS_TAG,0);
        sponsorDataCall.enqueue(new Callback<SponsorData>() {
            @Override
            public void onResponse(Call<SponsorData> call, Response<SponsorData> response) {
                DBHelper helper = new DBHelper(getApplicationContext());
                helper.deleteSponsorTable();
                List<SponsorSet> sponsorSets = response.body().getData();
                Log.e(TAG,sponsorSets.size()+"");
                for (int i = 0 ; i<sponsorSets.size();i++){
                    helper.addSponsorData(sponsorSets.get(i).getTitle(),sponsorSets.get(i).getUrl(),sponsorSets.get(i).getSponsUrl(),sponsorSets.get(i).getUpdatedAt());
//                    Log.e(TAG,success+"");
                }
            }

            @Override
            public void onFailure(Call<SponsorData> call, Throwable t) {
                Log.e(TAG,"Check your internet connection");
            }
        });
    }
}
