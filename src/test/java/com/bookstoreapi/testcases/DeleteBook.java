package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteBook {
    private static int requiredBookId;
    @Test(priority = 1,description = "Delete book by id")
    public void testDeleteBookById(){
                requiredBookId=GetAllTheBooks.getRandomBookId();
                new BookStoreAPI()
                .deleteBookById(SpecBuilder.getAuthHeader(LoginForAccessToken.getAccessToken()),Map.of("bookId",requiredBookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("message",equalTo("Book deleted successfully"));
    }

    @Test(priority = 2,description = "Delete book by id which is already deleted")
    public void testDeleteBookWhichIsAlreadyDeleted(){
        new BookStoreAPI()
                .deleteBookById(SpecBuilder.getAuthHeader(LoginForAccessToken.getAccessToken()),Map.of("bookId",requiredBookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(404)
                .body("detail",equalTo("Book not found"));
    }

    @Test(priority = 3,description = "Delete All books")
    public void testDeleteAllBooks(){
                GetAllTheBooks.getAllBooks()
                        .stream()
                        .map(Book::id)
                        .forEach( bookId-> new BookStoreAPI().deleteBookById(SpecBuilder.getAuthHeader(LoginForAccessToken.getAccessToken()),Map.of("bookId",bookId))
                        .then()
                        .spec(SpecBuilder.basicResponseSpec())
                        .statusCode(200)
                        .body("message",equalTo("Book deleted successfully")));
    }
}
