package com.pezesha.service;

import com.pezesha.model.Account;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
public interface AccountService extends IService<Account> {
    Account deposit(String accountNo, float amount);
}
