package com.rest.spotify.api;


import com.rest.spotify.utils.ConfigLoader;
import io.restassured.response.Response;
import java.time.Instant;
import java.util.HashMap;

import static com.rest.spotify.api.Route.API;
import static com.rest.spotify.api.Route.TOKEN;


public class TokenManager {
    private static String accessToken;
    private static Instant expiryTime;

    public synchronized static String getToken() {
        try {
            if (accessToken == null || Instant.now().isAfter(expiryTime)) {
                System.out.println("Renewing Token.......");
                Response response = renewToken();
                accessToken = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is good to use");
            }
        }
    catch(Exception e){
                e.printStackTrace();
                throw new RuntimeException("ABORT!!! Failed to get token");
            }
            return accessToken;
        }

    private static Response renewToken()
    {
        HashMap<String,String> formParams=new HashMap<String,String>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret",ConfigLoader.getInstance().getClientSecret());
        formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formParams.put("grant_type",ConfigLoader.getInstance().getGrantType());

        Response response=RestResources.postToken(formParams,API+TOKEN);

        if(response.statusCode() != 200){
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }
        return response;
    }
}
