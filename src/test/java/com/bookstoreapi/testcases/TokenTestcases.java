package com.bookstoreapi.testcases;

import com.bookstoreapi.Data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import org.testng.annotations.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class TokenTestcases {
    @Test(priority = 12,description = "Check book list with expired token")
    public void testExpireToken(){
        new BookStoreAPI()
                .getAllTheBooks(Map.of(),Map.of("Authorization","Bearer "+ UserData.expiredToken),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(403)
                .body("detail",equalTo("Invalid token or expired token"));
    }
    @Test(priority = 13,description = "Check book list with invalid token")
    public void invalidTokenCheckBookList(){
        new BookStoreAPI()
                .getAllTheBooks(Map.of(),Map.of("Authorization","Bearer "+"invalidToken"+LoginForAccessToken.accessToken),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(403)
                .body("detail",equalTo("Invalid token or expired token"));
    }

    @Test(priority = 14,description = "Check book list with no token")
    public void noTokenCheckBookList(){
        new BookStoreAPI()
                .getAllTheBooks(Map.of(),Map.of(),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(403)
                .body("detail",equalTo("Not authenticated"));
    }
}
