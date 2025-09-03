package com.bookstoreapi.model.request;

public record Book(
     int id,
     String name,
     String author,
     int published_year,
     String book_summary){
}
