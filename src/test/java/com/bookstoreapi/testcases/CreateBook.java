package com.bookstoreapi.testcases;

import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

import static com.bookstoreapi.testcases.LoginForAccessToken.getAccessToken;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class CreateBook {
   private static int bookId;
    @Test(description = "Create new Book")
    public void testCreateBook(){
        Book book=new BookStoreAPI()
                .createBook(UserData.book,Map.ofEntries(getAccessToken()))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"))
                .extract()
                .as(Book.class);
        Assert.assertEquals(book,UserData.book,"Created book not matching");
            bookId=book.id();
    }

    @Test(description = "Verifying newly added book")
    public void testVerifyAddedBook(){
       Book book= new BookStoreAPI()
                .verifyAddedBookById(Map.of(),Map.ofEntries(getAccessToken()),Map.of("bookId",bookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"))
                .extract()
                .as(Book.class);
        Assert.assertEquals(book,UserData.book,"Book not found");
    }
}
