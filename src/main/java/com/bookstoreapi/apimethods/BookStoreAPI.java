package com.bookstoreapi.apimethods;

import com.bookstoreapi.apiendpoints.APIEndpoints;
import io.restassured.response.Response;

import java.text.MessageFormat;
import java.util.Map;


public class BookStoreAPI extends BaseAPI{

    public Response checkServer(Map<String,?> queryParams,Map<String,String> headers,Map<String,?> pathParams){
        return get(APIEndpoints.HEALTH,queryParams,headers,pathParams);
    }
    public Response userSignUp(Object body,Map<String,String> headers){
        return post(APIEndpoints.SIGNUP,body,headers);
    }
    public Response loginUsingNewUser(Object body,Map<String,String> headers){
        return post(APIEndpoints.LOGIN,body,headers);
    }
    public Response getAllTheBooks(Map<String,?> queryParams,Map<String,String> headers,Map<String,?> pathParams){
       return get(APIEndpoints.GET_ALL_BOOKS,queryParams,headers,pathParams);
    }
    public Response createBook(Object body,Map<String,String> headers){
        return post(APIEndpoints.CREATE_BOOK,body,headers);
    }
    public Response verifyAddedBookById(Map<String,?> queryParams,Map<String,String> headers,Map<String,?> pathParams){
        return get(APIEndpoints.GET_BOOK,queryParams,headers,pathParams);
    }

    public Response updateBookById(Object body,Map<String,String> headers,Map<String,?> pathParams){
        return put(APIEndpoints.UPDATE_BOOK,body,headers,pathParams);
    }

    public Response deleteBookById(Map<String,String> headers,Map<String,?> pathParams){
        return delete(APIEndpoints.DELETE_BOOK,headers,pathParams);
    }
    public void deleteAllBooks(Map<String,String> headers){
     Response response=getAllTheBooks(Map.of(),headers,Map.of());
     response.jsonPath()
             .getList("id",Integer.class)
             .forEach( bookId->{
                deleteBookById(headers,Map.of("bookId",bookId))
                        .then()
                        .log().all()
                        .statusCode(200);
             });
    }

}
