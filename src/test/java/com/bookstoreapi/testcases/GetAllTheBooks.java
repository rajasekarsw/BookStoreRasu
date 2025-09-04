package com.bookstoreapi.testcases;

import com.bookstoreapi.apimethods.BookStoreAPI;
import com.bookstoreapi.apimethods.SpecBuilder;
import com.bookstoreapi.model.request.Book;
import io.restassured.common.mapper.TypeRef;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class GetAllTheBooks {
    public static List<Integer> bookIdlist;
    @Test(description = "Get book list")
    public void testGetAllTheBooks(){
       getAllBooks();
    }

    public static List<Book> getAllBooks(){
          return  new BookStoreAPI()
                .getAllTheBooks(Map.of(),SpecBuilder.getAuthHeader(LoginForAccessToken.getAccessToken()),Map.of())
                .then()
                .spec(SpecBuilder.basicResponseSpec())
                .statusCode(200)
                .extract()
                .as(new TypeRef<>() {});
    }

    public static int getRandomBookId(){
       return GetAllTheBooks.getAllBooks().stream().map(Book::id).findAny().orElseThrow();
    }


}
