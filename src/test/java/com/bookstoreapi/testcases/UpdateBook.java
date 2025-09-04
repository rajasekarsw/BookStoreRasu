package com.bookstoreapi.testcases;

import com.bookstoreapi.data.UserData;
import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import org.testng.annotations.Test;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class UpdateBook {
    @Test(description = "Update book by id")
    public void testUpdateBook(){
        int requiredBookId = GetAllTheBooks.getRandomBookId();
        Book updatedBook=UserData.getUpdatedBook(requiredBookId);
        new BookStoreAPI()
                .updateBookById(updatedBook, SpecBuilder.getAuthHeader(LoginForAccessToken.getAccessToken()),Map.of("bookId", requiredBookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body("id",equalTo(updatedBook.id()))
                .body("name",equalTo(updatedBook.name()))
                .extract()
                .as(Book.class);
    }
}
