package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    UserPojo userPojo = new UserPojo();
            static String name ="Sheela "+ TestUtils.randomStr(4);
            static String email = "Ram"+TestUtils.getRandomValue()+"@gmail.com";
            static String gender="female";
            static String status="active";
          String token ="f21064fd1d349031f50e15b7c8dfccd2a87dd140ee997ba970ffcd3646d6b690";
       static int newid;
    @Test
    public void test001(){


        Response response = given()
                .when()
                .get("?page=1&per_page=20");
                response.then().log().all().statusCode(200);

      }

    @Test
    public void test002(){

      userPojo.setName(name);
      userPojo.setEmail(email);
      userPojo.setStatus(status);
      userPojo.setGender(gender);
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization", "Bearer "+token)
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post();
                 response.prettyPrint();
                 response.then().log().all();
                 String status1 =response.jsonPath().get("status").toString();
                 String name1 = response.jsonPath().get("name").toString();
                 System.out.println(status1);
                 newid = response.then().extract().path("id");
                 System.out.println("id "+newid);

                  Assert.assertEquals("active",status1);
                  Assert.assertEquals(name , name1 );




    }
    @Test()
    public void test003() {

        int id = userPojo.getId();
        System.out.println("id "+id );
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization", "Bearer "+token)
                .pathParams("id",newid)
                .when()
                .get("/{id}");

        response.prettyPrint();
        response.then().log().all().statusCode(200);
        Assert.assertEquals(200,response.statusCode());

    }
@Test
    public void test004(){

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setStatus("inactive");
        userPojo.setGender("male");
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization", "Bearer "+token)
                .contentType(ContentType.JSON)
                .pathParams("id",newid)
                .when()
                .body(userPojo)
                .put("/{id}");
        response.prettyPrint();
        response.then().log().all().statusCode(200);
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void test005(){
        String token ="f21064fd1d349031f50e15b7c8dfccd2a87dd140ee997ba970ffcd3646d6b690";
        Response response = given()
                .header("Content-Type","application/json")
                .header("Access","application/json")
                .header("Authorization", "Bearer "+token)
                .contentType(ContentType.JSON)
                .pathParams("id",newid)
                .when()
                .delete("/{id}");
        response.prettyPrint();
        response.then().log().all().statusCode(204);
        Assert.assertEquals(204,response.getStatusCode());

    }
}
