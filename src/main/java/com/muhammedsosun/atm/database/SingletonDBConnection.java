package com.muhammedsosun.atm.database;


import com.muhammedsosun.atm.utils.SpecialColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDBConnection {

    private static final String URL = "jdbc:h2:./h2db/blog" + "AUTO_SERVER=TRUE";
    private static final String USERNAME ="sa";
    private static final String PASSWORD="";

    private SingletonDBConnection(){

    }
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null|| connection.isClosed()){
            try {
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                System.out.println(SpecialColor.GREEN + "Veritabanı bağlantısı başarılı" + SpecialColor.RESET);
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
                System.out.println(SpecialColor.GREEN + "Veritabanı bağlantısı başarısız" + SpecialColor.RESET);
                throw new RuntimeException("Veritabanı bağlantısı başarısız");
            }
        }
        return connection;
    }
}
