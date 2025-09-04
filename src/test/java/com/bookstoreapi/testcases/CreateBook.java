package com.bookstoreapi.testcases;

import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CreateBook {
   private static int bookId;
    @Test(description = "Create new Book",dependsOnMethods = "testLoginUsingNewUser")
    public void testCreateBook(){
        bookId=new BookStoreAPI()
                .createBook(UserData.book,SpecBuilder.getAuthHeader(LoginForAccessToken.getAccessToken()))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("id",equalTo(UserData.book.id()))
                .body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"))
                .extract()
                .as(Book.class)
                .id();
    }

    @Test(description = "Verifying newly added book")
    public void testVerifyAddedBook(){

        new BookStoreAPI()
                .verifyAddedBookById(Map.of(),SpecBuilder.getAuthHeader(LoginForAccessToken.getAccessToken()),Map.of("bookId",bookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("id",equalTo(bookId))
                .extract()
                .as(Book.class);
    }
}
