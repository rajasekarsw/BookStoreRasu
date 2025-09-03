package com.bookstoreapi.testcases;

import com.bookstoreapi.Data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import org.testng.annotations.Test;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class UserSignUpClass {

    @Test(priority = 2,description = "User sign up")
    public void testUserSignUp(){
        new BookStoreAPI()
                .userSignUp(UserData.signUp, Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("message",equalTo("User created successfully"));
    }

    @Test(priority = 3,dependsOnMethods = "testUserSignUp",description = "User sign up with existing users")
    public void testSignUpWithExistingUser(){
        new BookStoreAPI()
                .userSignUp(UserData.signUp, Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(400)
                .body("detail",equalTo("Email already registered"));
    }
}
