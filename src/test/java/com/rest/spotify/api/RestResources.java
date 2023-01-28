package com.rest.spotify.api;


import io.restassured.response.Response;
import java.util.HashMap;

import static com.rest.spotify.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResources {

    public static Response post(String path,Object reqPlayList, String accessToken )
    {
        return  given(getRequestSpec()).
                auth().oauth2(accessToken).
                body(reqPlayList).
                when().
                post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path,String accessToken)
    {
        return given(getRequestSpec()).
                auth().oauth2(accessToken).
                when().
                get(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response update(String path,Object reqPlayList,String accessToken)
    {
        return given(getRequestSpec()).
                auth().oauth2(accessToken).
                body(reqPlayList).
                when().
                put(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response postToken(HashMap<String,String> formParams,String path)
    {
        return given(getAccountRequestSpec()).
                formParams(formParams).
                when().
                post(path).
                then().spec(getResponseSpec()).
                extract().response();
    }
}
