package com.bookstoreapi.testcases;

import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import org.testng.annotations.Test;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class UserSignUpClass {

    @Test(description = "User sign up")
    public void testUserSignUp(){
        new BookStoreAPI()
                .userSignUp(UserData.signUp, Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/Message.json"))
                .body("message",equalTo("User created successfully"));
    }

    @Test(dependsOnMethods = "testUserSignUp",description = "User sign up with existing users")
    public void testSignUpWithExistingUser(){
        new BookStoreAPI()
                .userSignUp(UserData.signUp, Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(400)
                .body(matchesJsonSchemaInClasspath("schemas/Detail.json"))
                .body("detail",equalTo("Email already registered"));
    }
}
