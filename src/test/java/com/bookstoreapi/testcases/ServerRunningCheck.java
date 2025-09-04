package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import org.testng.annotations.Test;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class ServerRunningCheck {

    @Test(description = "Checking the server is running")
    public void testCheckServer(){
        new BookStoreAPI().checkServer(Map.of(),Map.of(),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("status",equalTo("up"));
    }
}
