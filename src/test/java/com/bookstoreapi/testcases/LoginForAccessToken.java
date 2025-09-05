package com.bookstoreapi.testcases;

import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.UserSignUp;
import com.bookstoreapi.model.response.LoginForAccessTokenResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.*;
import static org.hamcrest.Matchers.equalTo;

public class LoginForAccessToken {

    private static Map.Entry<String,String> accessToken;
    @Test(dependsOnMethods = {"testUserSignUp"},description = "Login with new user and get access token")
    public void testLoginUsingNewUser() {
        accessToken=getAccessToken();
    }

    @Test(dataProvider="invalidUsers",description = "Login with invalid credentials")
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
    return UserData.invalidUsers.iterator();
    }

   public static Map.Entry<String,String> getAccessToken(){

       if(accessToken==null) {
           LoginForAccessTokenResponse tokenResponse = new BookStoreAPI()
                   .loginUsingNewUser(UserData.signUp, Map.of())
                   .then()
                   .spec(SpecBuilder.basicResponseSpec())
                   .statusCode(200)
                   .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/AccessToken.json"))
                   .extract()
                   .as(LoginForAccessTokenResponse.class);
           accessToken=Map.entry("Authorization",tokenResponse.token_type()+" "+tokenResponse.access_token());
       }
        return accessToken;
    }
}
