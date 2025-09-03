package com.bookstoreapi.data;

import com.bookstoreapi.model.request.Book;
import com.bookstoreapi.model.request.UserSignUp;

import java.util.List;

public class UserData {
public static UserSignUp signUp=new UserSignUp(Datagenerator.randomID(),Datagenerator.randomEmail(),Datagenerator.randomPwd());
public static Book book=new Book(Datagenerator.randomID(),"Agni"+Datagenerator.randomID(),"Abdul kalam",Datagenerator.randomYear(),"life about kalam");
public static Book updateBook=new Book(book.id(),"Updated Agni"+Datagenerator.randomID(),"update author Abdul kalam",Datagenerator.randomYear(),"life about kalam");

public static List<UserSignUp> listOfInvalidUsers=
                    List.of(
                    // invalid id not considered as invalid id, so ignored
                    // invalid email
                    new UserSignUp(UserData.signUp.id(), null, UserData.signUp.password()),
                    new UserSignUp(UserData.signUp.id(), "", UserData.signUp.password()),
                    new UserSignUp(UserData.signUp.id(), "invalidEmail", UserData.signUp.password()),
                    new UserSignUp(UserData.signUp.id(), "user@domain", UserData.signUp.password()),

                    // invalid password
                    new UserSignUp(UserData.signUp.id(), UserData.signUp.email(), ""),
                    new UserSignUp(UserData.signUp.id(), UserData.signUp.email(), "PASSword123"),

                    // combined invalids
                    new UserSignUp(0, null, null),
                    new UserSignUp(-1, "invalidEmail", "short"),
                    new UserSignUp(UserData.signUp.id(), "", "")
            );

public static String expiredToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMzQ2MkBleGFtcGxlLmNvbSIsImV4cCI6MTc1Njg2NjM2N30.Jx6V9E-UpTfEeFjFvEd7kxSsRHsmqYZuk3PuEFlhLcs";

public static String incorrectPayloadFormat= """
        {
            "name": "Agni79651",
            "published_year": 1956,
            "author": "Abdul kalam",
            "id": 73971,
            "book_summary": "life about kalam",
        }
        """;

}
