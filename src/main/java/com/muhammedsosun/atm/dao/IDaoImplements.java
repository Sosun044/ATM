package com.muhammedsosun.atm.dao;

import com.muhammedsosun.atm.database.SingletonDBConnection;

import java.sql.Connection;

public interface IDaoImplements<T> extends ICrud<T>,IGenericsMethod<T>,ILogin<T>{
    default Connection iDaoImplementsDatabaseConnection(){
        return SingletonDBConnection.getInstance().getConnection();
    }
}
