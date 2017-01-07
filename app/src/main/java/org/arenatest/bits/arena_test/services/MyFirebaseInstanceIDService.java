package org.arenatest.bits.arena_test.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.arenatest.bits.arena_test.api.ApiClient;
import org.arenatest.bits.arena_test.api.ApiInterface;
import org.arenatest.bits.arena_test.app.Constants;
import org.arenatest.bits.arena_test.sets.TokenItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tejeshwar on 18/12/16.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{

    private static final String REG_TOKEN = "REG_TOKEN";

    ApiInterface apiInterface;
    @Override
    public void onTokenRefresh() {
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN,recent_token);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (recent_token!=null) {
            final Call<TokenItem> tokenItemCall = apiInterface.addToken(Constants.TOKEN_TAG,recent_token);
            tokenItemCall.enqueue(new Callback<TokenItem>() {
                @Override
                public void onResponse(Call<TokenItem> call, Response<TokenItem> response) {
                    Log.e(REG_TOKEN,response.message());
                }

                @Override
                public void onFailure(Call<TokenItem> call, Throwable t) {

                }
            });
        }
    }
}
