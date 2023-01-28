package com.rest.spotify.test;


import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {
    @BeforeMethod
    public void beforeTest(Method m)
    {
        System.out.println("Test Execution..." +m.getName());
        System.out.println("Thread Id...:" +Thread.currentThread().getId());
    }
}
