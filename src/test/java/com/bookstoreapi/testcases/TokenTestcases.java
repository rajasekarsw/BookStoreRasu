package com.bookstoreapi.testcases;

import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import org.testng.annotations.Test;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class TokenTestcases {
    @Test(description = "Check book list with expired token")
    public void testExpireToken(){
        new BookStoreAPI()
                .getAllTheBooks(Map.of(),SpecBuilder.getAuthHeader( UserData.expiredToken),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(403)
                .body("detail",equalTo("Invalid token or expired token"));
    }
    @Test(description = "Check book list with invalid token")
    public void invalidTokenCheckBookList(){
        new BookStoreAPI()
                .getAllTheBooks(Map.of(),SpecBuilder.getAuthHeader("invalidToken"+LoginForAccessToken.getAccessToken()),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(403)
                .body("detail",equalTo("Invalid token or expired token"));
    }

    @Test(description = "Check book list with no token")
    public void noTokenCheckBookList(){
        new BookStoreAPI()
                .getAllTheBooks(Map.of(),Map.of(),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(403)
                .body("detail",equalTo("Not authenticated"));
    }
}
