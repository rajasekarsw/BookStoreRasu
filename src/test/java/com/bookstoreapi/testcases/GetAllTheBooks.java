package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.Header;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllTheBooks {

    @Test(priority = 6,description = "Get book list")
    public void testGetAllTheBooks(){
                new BookStoreAPI()
                .getAllTheBooks(Map.of(),Map.of("Authorization","Bearer "+LoginForAccessToken.accessToken),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<Book>>() {});
    }


}
