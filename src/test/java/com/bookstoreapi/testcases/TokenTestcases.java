package com.bookstoreapi.testcases;

import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.response.InvalidTokenResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Iterator;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class TokenTestcases {

    @Test(dataProvider ="provideInvalidToken",description = "Invalid token test")
    public void invalidTokens(InvalidTokenResponse tokenResponse){
        new BookStoreAPI()
                .getAllTheBooks(Map.of(),tokenResponse.token(),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(tokenResponse.responseCode())
                .body(matchesJsonSchemaInClasspath("schemas/Detail.json"))
                .body("detail",equalTo(tokenResponse.responseMessage()));
    }

    @DataProvider
    public Iterator<InvalidTokenResponse> provideInvalidToken(){
      return UserData
              .getInvalidTokens(LoginForAccessToken.getAccessToken()
              .getValue().split(" ")[0]+" ").iterator();
    }

}
