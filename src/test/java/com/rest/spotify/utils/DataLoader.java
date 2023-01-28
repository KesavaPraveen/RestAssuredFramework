package com.rest.spotify.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = PropertyUtil.propertyLoader("src/test/resources/data.properties");
    }

    public static DataLoader getInstance(){
        if(dataLoader == null){
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public String getPlaylistId(){
        String prop = properties.getProperty("getPlayListId");
        if(prop != null) return prop;
        else throw new RuntimeException("property getPlayListId is not specified in the data.properties file");
    }

    public String updatePlaylistId(){
        String prop = properties.getProperty("updatePlayListId");
        if(prop != null) return prop;
        else throw new RuntimeException("property updatePlaylistId is not specified in the data.properties file");
    }
}
