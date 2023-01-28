package com.rest.spotify.utils;

import com.github.javafaker.Faker;

public class FakerUtil {
    private static String name;
    private static String description;

    public static String generateName()
    {
        Faker faker=new Faker();
        return "PlayList" + faker.regexify("[A-Za-z0-9,_-]{20}");
    }

    public static String generateDescription()
    {
        Faker faker=new Faker();
        return "Description" +faker.regexify("[A-Za-z0-9,&*$%@_-]{60}");
    }
}
