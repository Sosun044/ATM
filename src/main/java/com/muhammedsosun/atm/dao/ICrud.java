package com.muhammedsosun.atm.dao;

import java.util.List;
import java.util.Optional;

public interface ICrud<T> {
    //CREATE
    Optional<T> create(T t);

    //LIST
    Optional<List<T>> list();

    //FIND
    Optional<T> findByName(String name);
    Optional<T> findById(int id);

    //UPDATE
    Optional<T> update(int id,T t);

    //DELETE
    Optional<T> delete(int id);


}
