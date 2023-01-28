package com.rest.spotify.test;

import com.rest.spotify.api.StatusCode;
import com.rest.spotify.api.applicationApi.PlaylistApi;
import com.rest.spotify.pojos.Error;
import com.rest.spotify.pojos.PlayList;
import com.rest.spotify.utils.ConfigLoader;
import com.rest.spotify.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.rest.spotify.utils.FakerUtil.generateDescription;
import static com.rest.spotify.utils.FakerUtil.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify OAuth2.0 PlayList Automation")
@Feature("End to End Framework Creation for Spotify")
public class PlaylistTest extends BaseTest{

    @Story("Playlist Creation")
    @Description("This Test case will create a new playlist and validates the status code," +
            "playlist name, playlist description and playlist public")
    @Test(description = "Verify whether the API is able to create a Playlist")
    public void validateCreateAPlaylist()
    {
        PlayList reqPlayList=playListBuilder(generateName(),generateDescription(),false);
        Response response=PlaylistApi.post(reqPlayList);
        assertStatusCode(response.statusCode(),StatusCode.CODE_201);
        assertPlayList(response.as(PlayList.class),reqPlayList);
    }

    @Story("Getting Playlist Details")
    @Link("We can mention the Link here....")
    @Description("This Test case will get the details of a playlist using the playlist ID"+
            "and validates the status code,playlist name, description and public")
    @Test(description = "Verify whether the API is able to fetch the details of the Playlist")
    public void validateGetAPlaylistDetails()
    {
        PlayList reqPlayList=playListBuilder("PlayList Automation","Automation Playlist description",false);
        Response response=PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);
        assertPlayList(response.as(PlayList.class),reqPlayList);
    }

    @Story("Updating Playlist Details")
    @Issue("We can mention the Defect URL here......")
    @Description("This Test case will update the details of a playlists name,description and public" +
            "and validates its status code using the playlist ID")
    @Test(description = "Verify whether the API is able to update a Playlist")
    public void validateUpdatePlaylistDetails()
    {
        PlayList reqPlayList=playListBuilder(generateName(),generateDescription(),false);
        Response response=PlaylistApi.update(DataLoader.getInstance().updatePlaylistId(), reqPlayList);
        assertStatusCode(response.statusCode(),StatusCode.CODE_200);
    }

    @Story("Error Validation")
    @TmsLink("We can mention Test Case ID here......")
    @Description("This test will validate the error response if the playlist name is passed as an empty" +
            "String in the request body")
    @Test(description = "Verify whether the API is able to validate the error response developed for Playlist")
    public void validateErrorResponseForCretePlaylist()
    {
        PlayList reqPlayList=playListBuilder("","",false);
        Response response=PlaylistApi.post(reqPlayList);
        assertErrorResponse(response.as(Error.class),StatusCode.CODE_400);
    }

    @Story("Error Validation")
    @Description("This test will validate the error response if the user passes invalid token")
    @Test(description = "Verify whether the API is able to validate the error response for Invalid Token")
    public void validateErrorResponseForInvalidAccessToken()
    {
        String token ="123";
        Response response=PlaylistApi.get(ConfigLoader.getInstance().getClientId(),token);
        assertErrorResponse(response.as(Error.class),StatusCode.CODE_401);
    }

    @Step
    public PlayList playListBuilder(String name, String description, Boolean _public)
    {
        return PlayList.builder().name(name).
                description(description).
                _public(_public).
                build();
    }

    @Step
    public void assertPlayList(PlayList responsePlayList,PlayList reqPlayList)
    {
        assertThat(responsePlayList.getName(),equalTo(reqPlayList.getName()));
        assertThat(responsePlayList.getDescription(),equalTo(reqPlayList.getDescription()));
        assertThat(responsePlayList.get_public(),equalTo(reqPlayList.get_public()));
    }

    @Step
    public void assertErrorResponse(Error error,StatusCode statusCode)
    {
        assertThat(error.getError().getStatus(),equalTo(statusCode.code));
        assertThat(error.getError().getMessage(),equalTo(statusCode.msg));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statusCode)
    {
        assertThat(actualStatusCode,equalTo(statusCode.code));
    }
}
