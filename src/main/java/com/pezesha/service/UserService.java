package com.pezesha.service;

import com.pezesha.model.User;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
public interface UserService extends IService<User>{
    User findByUsername(String email);
}
