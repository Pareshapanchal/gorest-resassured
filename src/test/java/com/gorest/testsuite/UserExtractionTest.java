package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
         RestAssured.baseURI ="https://gorest.co.in";
         RestAssured.basePath= "/public/v2/users";
        response= given()
                .when()
                .get("?page=1&per_page=20")
                .then().statusCode(200);

    }
        //1. Extract the All Ids
    @Test
    public void test001(){
        List<Integer> allIds = response.extract().path("id");
        System.out.println("Print All Ids  : \n"+allIds);
    }
        //2. Extract the all Names
        @Test
        public void test002(){
        List<String> allNames = response.extract().path("name");
            System.out.println("Print all names  :\n"+ allNames);
        }
        //3. Extract the name of 5th object
        @Test
        public void test003(){
        String name = response.extract().path("[4].name");
            System.out.println("Name of 5th object  : "+name);
        }
        //4. Extract the names of all object whose status = inactive
        @Test
        public void test004(){
        List<String> names = response.extract().path("findAll{it.status=='inactive'}.name");
            System.out.println("Names of all object whose status = inactive :\n"+names);
        }
        //5. Extract the gender of all the object whose status = active
        @Test
        public void test005(){
        List<String> male = response.extract().path("findAll{it.status=='active'}.gender");
            System.out.println("List gender of all the object whose status is active  :\n"+male);
        }
        //6. Print the names of the object whose gender = female
        @Test
        public void test006(){
        List<String > names = response.extract().path("findAll{it.gender=='female'}.name");
            System.out.println("Names of the object whose gender is female :\n"+names);
        }
        //7. Get all the emails of the object where status = inactive
        @Test
        public void test007(){
        List<String> emails= response.extract().path("findAll{it.status == 'inactive'}.email");
            System.out.println("List of emails of the object where status is inactive :\n"+emails);
        }
        //8. Get the ids of the object where gender = male
        @Test
        public void test008(){
        List<Integer> ids = response.extract().path("findAll{it.gender =='male'}.id");
            System.out.println("List all ids of the object where gender is male :\n"+ ids);
        }
        //9. Get all the status
        @Test
        public void test009(){
        List<String> status = response.extract().path("status");
            System.out.println("List of status  :\n"+status);
        }
        //10. Get email of the object where name = Karthik Dubashi IV
        @Test
        public void test010(){
        String emails= response.extract().path("find{it.name=='Pranay Reddy'}.email");
            System.out.println("Email of the object where name is Pranay Reddy  :\n"+emails);
       }
        //11. Get gender of id = 5471
        @Test
        public void test011(){response.extract().path("");}
}
