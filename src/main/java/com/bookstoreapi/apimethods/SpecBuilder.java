package com.bookstoreapi.apimethods;

import com.bookstoreapi.utils.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification baseSpec(){

        return new RequestSpecBuilder()
                           .setBaseUri(ConfigReader.getConfigValue("url"))
                           .log(LogDetail.ALL)
                           .setContentType(ContentType.JSON)
                           .build();
    }

    public static ResponseSpecification basicResponseSpec(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

}
