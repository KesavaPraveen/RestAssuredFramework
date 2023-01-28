package com.rest.spotify.api.applicationApi;


import com.rest.spotify.api.RestResources;
import com.rest.spotify.pojos.PlayList;
import com.rest.spotify.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.rest.spotify.api.Route.PLAYLISTS;
import static com.rest.spotify.api.Route.USERS;
import static com.rest.spotify.api.TokenManager.getToken;

public class PlaylistApi {

    @Step
    public static Response post(PlayList reqPlayList)
    {
        return RestResources.post(USERS+"/"+ ConfigLoader.getInstance().getUser()+PLAYLISTS,
                reqPlayList, getToken());
    }

    @Step
    public static Response get(String playListId)
    {
        return RestResources.get(PLAYLISTS+"/"+playListId,getToken());
    }

    @Step
    public static Response update(String playListId,PlayList reqPlayList)
    {
        return RestResources.update(PLAYLISTS+"/"+playListId,reqPlayList,getToken());
    }

    @Step
    public static Response get(String playListId,String token)
    {
        return RestResources.get(PLAYLISTS+"/"+playListId,token);
    }
}
