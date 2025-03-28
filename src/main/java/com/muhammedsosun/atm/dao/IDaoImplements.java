package com.muhammedsosun.atm.dao;

import com.muhammedsosun.atm.database.SingletonPropertiesDBConnection;

import java.sql.Connection;

public interface IDaoImplements<T> extends ICrud<T>,IGenericsMethod<T>{
    default Connection iDaoImplementsDatabaseConnection(){
        return SingletonPropertiesDBConnection.getInstance().getConnection();
    }
}
