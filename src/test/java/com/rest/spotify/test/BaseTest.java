package com.rest.spotify.test;


import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {
    @BeforeMethod
    public void beforeTest(Method m)
    {
        //Added the below statement to check the GITHUB Webhook Trigger
        System.out.println("The Pipeline is running thorugh Jenkins Job and through Git Hub WebHook Process");

        System.out.println("Test Execution..." +m.getName());
        System.out.println("Thread Id...:" +Thread.currentThread().getId());
    }
}
