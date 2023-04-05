package com.pezesha.service;

import com.pezesha.model.Transfer;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/5/23, Wednesday
 **/
public interface TransferService {
    Transfer transfer(String fromAccountNo, String toAccountNo, float amount);
}
