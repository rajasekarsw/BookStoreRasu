package com.bookstoreapi.testcases;

import com.bookstoreapi.Data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.UserSignUp;
import com.bookstoreapi.model.response.LoginForAccessTokenResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginForAccessToken {
    public static String accessToken;

    @Test(priority = 4, dependsOnMethods = {"testUserSignUp"},description = "Login with new user")
    public void testLoginUsingNewUser() {

        accessToken = new BookStoreAPI()
                .loginUsingNewUser(UserData.signUp, Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .extract()
                .as(LoginForAccessTokenResponse.class)
                .accessToken();
    }

    @Test(priority = 5,dataProvider="invalidUsers",description = "Login with invalid credentials")
    public void testLoginUsingInvalidCreds(UserSignUp login) {
        new BookStoreAPI()
                .loginUsingNewUser(login, Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(400)
                .body("detail",equalTo("Incorrect email or password"));
    }

    @DataProvider
    public Iterator<UserSignUp> invalidUsers() {
    return UserData.listOfInvalidUsers.iterator();
    }

}
