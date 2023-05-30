package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {
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
        //1. Verify the if the total record is 25
        @Test
        public void test001(){response.body("id.size()",equalTo(25));}

        //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demittocentum.”
        @Test
        public void test002(){response.body("findAll{it.id == 39287}.title",hasItem("Stultus timor apparatus natus nesciunt."));}
        //3. Check the single user_id in the Array list (5522)
        @Test
        public void test003(){

                 response.body("user_id",hasItem(2302198));

    }
      //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
        @Test
        public void test004(){response.body("id",hasItems(39686,39671,39663));}
        //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
        // Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
        //    antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequuscuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
        //    adflicto. Assentator umquam pel."”
        @Test
        public void test005(){response.body("findAll{it.user_id == 2272657}.body",hasItem( "Illum vomito ventito. Auctus contego crebro. Desino comedo suffragium. Temporibus utor conqueror. Taedium eveniet aqua. Certus villa vulnero. Unde templum aliquid. Auctus illo calco. Ager cupressus cunae. Vallum suasoria bene. Explicabo cui uxor. Adhaero ascit sordeo. Ratione conspergo vigilo. Una arguo uberrime. Aegrus contabesco supellex. Asporto super ventito. Delicate auxilium velociter. Ultra videlicet subiungo. Thymum vilitas videlicet."));}
}
