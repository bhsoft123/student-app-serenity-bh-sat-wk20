package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.annotations.WithTags;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Jay
 */
@RunWith(SerenityRunner.class)
public class TagsTest extends TestBase {

    //negative test case // create   
    @WithTag("studentfeature:Negative")
    @Title("Provide or verify a 405 status code when incorrect HTTP method is used to access resource ")
    @Test
    public void invalidMethod() {
        SerenityRest.given()
                .when()
                .post("/list")
                .then()
                .statusCode(405)
                .log().all();
    }
    //positive test case
    @WithTags({
            @WithTag("studentfeature:Positive"),
            @WithTag("smoke")})
    @Title("This test will verify a 200 status code for GET request")
    @Test
    public void verifyStatusCodeIs200() {
        SerenityRest.given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .log().all();
    }
    //negative test case
    @WithTags({@WithTag("studentfeature:Negative"),
            @WithTag("smoke")})
    @Title("This test will provde error code 400 when user tries to access an invalid resource")
    @Test
    public void incorrect() {
        SerenityRest.given()
                .when()
                .get("/list123")
                .then()
                .statusCode(400)
                .log().all();
    }
}