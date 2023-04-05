package com.pezesha.service;

import java.util.List;
import java.util.UUID;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
public interface IService<T> {
    List<T> listAll();

    T getById(UUID id);

    T getById(Long id);

    T saveOrUpdate(T t);

    void delete(T t);

}
