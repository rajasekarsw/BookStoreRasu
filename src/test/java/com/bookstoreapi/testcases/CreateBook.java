package com.bookstoreapi.testcases;

import com.bookstoreapi.Data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CreateBook {
public static int bookId;
    @Test(priority = 7,description = "Create new Book")
    public void testCreateBook(){
        bookId=new BookStoreAPI()
                .createBook(UserData.book,Map.of("Authorization","Bearer "+LoginForAccessToken.accessToken))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("id",equalTo(UserData.book.id()))
                .body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"))
                .extract()
                .as(Book.class)
                .id();
    }

    @Test(priority = 8,description = "Verifying newly added book")
    public void testVerifyAddedBook(){

        new BookStoreAPI()
                .verifyAddedBookById(Map.of(),Map.of("Authorization","Bearer "+LoginForAccessToken.accessToken),Map.of("bookId",bookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("id",equalTo(bookId))
                .extract()
                .as(Book.class);
    }
}
