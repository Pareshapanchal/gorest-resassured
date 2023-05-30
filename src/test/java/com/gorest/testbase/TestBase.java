package com.gorest.testbase;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;

public class TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI ="https://gorest.co.in";
        RestAssured.basePath= "/public/v2/users";

    }
}
