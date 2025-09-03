package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteBook {

    @Test(priority = 10,description = "Delete book by id")
    public void testDeleteBookById(){
                new BookStoreAPI()
                .deleteBookById(Map.of("Authorization","Bearer "+LoginForAccessToken.accessToken),Map.of("bookId",CreateBook.bookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("message",equalTo("Book deleted successfully"));
    }

    @Test(priority = 11,description = "Delete book by id which is already deleted")
    public void testDeleteBookWhichIsAlreadyDeleted(){
        new BookStoreAPI()
                .deleteBookById(Map.of("Authorization","Bearer "+LoginForAccessToken.accessToken),Map.of("bookId",CreateBook.bookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(404)
                .body("detail",equalTo("Book not found"));
    }

    @Test(priority = 16,description = "Delete All books")
    public void testDeleteAllBooks(){
        new BookStoreAPI().getAllTheBooks(Map.of(),Map.of("Authorization","Bearer "+LoginForAccessToken.accessToken),Map.of())
                .jsonPath()
                .getList("id",Integer.class)
                .forEach( bookId-> new BookStoreAPI().deleteBookById(Map.of("Authorization","Bearer "+LoginForAccessToken.accessToken),Map.of("bookId",bookId))
                        .then()
                        .spec(SpecBuilder.basicResponseSpec())
                        .statusCode(200));
    }
}
