package com.bookstoreapi.testcases;

import com.bookstoreapi.Data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import org.testng.annotations.Test;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBook {

    @Test(priority = 9,description = "Update book by id")
    public void testUpdateBook(){
        new BookStoreAPI()
                .updateBookById(UserData.updateBook, Map.of("Authorization","Bearer "+LoginForAccessToken.accessToken),Map.of("bookId",CreateBook.bookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("id",equalTo(UserData.updateBook.id()))
                .body("name",equalTo(UserData.updateBook.name()))
                .extract()
                .as(Book.class);
    }
}
