package com.bookstoreapi.model.response;
import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginForAccessTokenResponse(@JsonProperty("access_token")String accessToken,@JsonProperty("token_type")String tokenType) {
}
