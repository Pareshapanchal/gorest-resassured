package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtreationTest {
    static ValidatableResponse response;
    @BeforeClass

    public static void test() {
        RestAssured.baseURI ="https://gorest.co.in";
        RestAssured.basePath= "/public/v2/posts";
        response= given()
                .when()
                .get("?page=1&per_page=25")
                .then().statusCode(200);

    }

        //1. Extract the title
    @Test
    public void test001(){
        List<String> titles= response.extract().path("title");
        System.out.println("All titles are :");
        for (String title: titles){
        System.out.println(title+",");}
    }
        //2. Extract the total number of record
    @Test
    public void test002(){
        int total = response.extract().path("total.size()");
        System.out.println("Total number of record : "+ total);
    }
        //3. Extract the body of 15th record
    @Test
    public void test003(){
        String record = response.extract().path("[14].body");
        System.out.println("Body of 15th record is : \n"+record);
    }
        //4. Extract the user_id of all the records
    @Test
    public void test004(){
        List<String> userid = response.extract().path("user_id");
        System.out.println("List of userids of all the records  :\n"+userid);
    }
        //5. Extract the title of all the records
    @Test
    public void test005(){
        List<String> titles = response.extract().path("title");
        System.out.println("List of titles of all the records :\n"+titles);
    }
        //6. Extract the title of all records whose user_id = 5456
    @Test
    public void test006(){
        List<String> title = response.extract().path("findAll{it.user_id==2272652}.title");
        System.out.println("The title of all the records whose user_id is 2272652 :\n"+title);
    }
        //7. Extract the body of all records whose id = 2671
        @Test
        public void test007(){
        List <String> body = response.extract().path("findAll{it.id ==39291}.body");
            System.out.println("The body of all the records whose id is 39291  :\n "+body);

        }
}
