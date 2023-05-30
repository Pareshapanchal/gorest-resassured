package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {
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
    //1. Verify the if the total record is 20
    @Test
    public void test001(){
            response.body("total.size()",equalTo(20));          //response.body("id", hasSize(20));
    }

    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002(){
        response.body("findAll{it.id = '2272622'}.name",hasItem("Smriti Pilla"));
    }
    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003(){
        response.body("name[2]",equalTo("Smriti Pilla"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void test004(){
        response.body("name",hasItems("Aasa Kaniyar II","Ganaka Prajapat DVM","Smriti Pilla"));}
    //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005(){response.body("findAll{it.id='2272622'}.email",hasItem("pilla_smriti@mcglynn.example"));}
    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
   @Test
    public void test006(){response.body("findAll{it.status == 'active'}.name",hasItem("Smriti Pilla"));}
    @Test
    //7. Verify the Gender = male of user name is “Niro Prajapat”
   public void test007(){response.body("findAll{it.gender='male'}.name",hasItem("Harinakshi Joshi"));}

}
