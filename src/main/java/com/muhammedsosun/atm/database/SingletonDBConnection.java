package com.muhammedsosun.atm.database;


import com.muhammedsosun.atm.utils.SpecialColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDBConnection {

    // private static final String URL = "jdbc:h2:./h2db/blog" + "AUTO_SERVER=TRUE";
    private static final String URL = "jdbc:h2:./h2db/user_management";
    private static final String USERNAME ="sa";
    private static final String PASSWORD="";

    private static SingletonDBConnection instance;
    private  Connection connection;

    private SingletonDBConnection(){
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println(SpecialColor.GREEN + "Veritabanı bağlantısı başarılı" + SpecialColor.RESET);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(SpecialColor.GREEN + "Veritabanı bağlantısı başarısız" + SpecialColor.RESET);
            throw new RuntimeException("Veritabanı bağlantısı başarısız");
        }
    }

    // Singleton Design Intance
    public static synchronized SingletonDBConnection getInstance(){
        if(instance==null){
            instance= new SingletonDBConnection();
        }
        return instance;
    }

    public  Connection getConnection(){
        return connection;
    }
    public static void closeConnection(){
        if (instance!= null && instance.connection != null){
            try {
                instance.connection.close();
                System.out.println(SpecialColor.RED+ "Veritabanı bağlantısı kapatıldı"+ SpecialColor.RESET);

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {

    }
}
