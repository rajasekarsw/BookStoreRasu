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

public class UpdateBook {
    @Test(description = "Update book by id")
    public void testUpdateBook(){
        int requiredBookId = GetAllTheBooks.getRandomBookId();
        Book updatedBook=UserData.getUpdatedBook(requiredBookId);
        Book book = new BookStoreAPI()
                .updateBookById(updatedBook, Map.ofEntries(getAccessToken()),Map.of("bookId", requiredBookId))
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"))
                .extract()
                .as(Book.class);
        Assert.assertEquals(book,updatedBook,"Updated book not matching");
    }
}
