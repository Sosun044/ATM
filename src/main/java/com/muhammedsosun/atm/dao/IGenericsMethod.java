package com.muhammedsosun.atm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface IGenericsMethod<T> {
    //Generic Method
    T mapToObjectDTO(ResultSet resultSet) throws SQLException;

    Optional<T> selectSingle(String sql, Object... params);
}
