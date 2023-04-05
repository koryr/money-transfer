package com.pezesha.payload;

import lombok.Data;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
@Data
public class JwtResponse {
    private String username;
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtResponse(String username, String accessToken) {
        this.accessToken = accessToken;
        this.username = username;
    }
}
