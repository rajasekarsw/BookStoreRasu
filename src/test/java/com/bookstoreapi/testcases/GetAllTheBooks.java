package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import static com.bookstoreapi.testcases.LoginForAccessToken.getAccessToken;

public class GetAllTheBooks {

    @Test(description = "Get book list")
    public void testGetAllTheBooks(){
       getAllBooks();
    }

    public static List<Book> getAllBooks(){
          return  new BookStoreAPI()
                .getAllTheBooks(Map.of(),Map.ofEntries(getAccessToken()),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/AllBookSchema.json"))
                .extract()
                .as(new TypeRef<>() {});
    }

    public static int getRandomBookId(){
       return getAllBooks().stream().map(Book::id).findAny().orElseThrow();
    }


}
