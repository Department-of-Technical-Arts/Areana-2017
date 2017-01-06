package bphc.tech.com.arena17.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import bphc.tech.com.arena17.api.ApiClient;
import bphc.tech.com.arena17.api.ApiInterface;
import bphc.tech.com.arena17.app.Constants;
import bphc.tech.com.arena17.database.DBHelper;
import bphc.tech.com.arena17.sets.SponsorData;
import bphc.tech.com.arena17.sets.SponsorSet;
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
