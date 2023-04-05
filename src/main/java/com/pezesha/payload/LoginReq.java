package com.pezesha.payload;

import lombok.Data;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
@Data
public class LoginReq {
    private String username;
    private String password;
    public LoginReq(){}
}
