package com.bookstoreapi.testcases;

import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class IncorrectJsonFormat {
    @Test(description = "Create book with invalid JSON payload")
    public void testIncorrectJsonPayload(){
               new BookStoreAPI()
                .createBook(UserData.incorrectPayloadFormat, SpecBuilder.getAuthHeader(LoginForAccessToken.getAccessToken()))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(422)
                .body(matchesJsonSchemaInClasspath("schemas/IncorrectJsonFormat.json"));
    }
}
