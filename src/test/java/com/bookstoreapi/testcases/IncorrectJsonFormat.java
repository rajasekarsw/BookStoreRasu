package com.bookstoreapi.testcases;

import com.bookstoreapi.Data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import org.testng.annotations.Test;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class IncorrectJsonFormat {
    @Test(priority = 15,description = "Create book with invalid JSON payload")
    public void testIncorrectJsonPayload(){
               new BookStoreAPI()
                .createBook(UserData.incorrectPayloadFormat, Map.of("Authorization","Bearer "+LoginForAccessToken.accessToken))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(422)
                .body(matchesJsonSchemaInClasspath("schemas/IncorrectJsonFormat.json"));
    }
}
