package com.muhammedsosun.atm.database;

import org.h2.tools.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SingletonPropertiesDBConnection {

    // Veritabanı bilgileri
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static SingletonPropertiesDBConnection instance;
    private Connection connection;

    // Constructor
    private SingletonPropertiesDBConnection() {
        try {
            loadDatabaseConfig(); // config.properties dosyasını oku
            Class.forName("org.h2.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Veritabanı bağlantısı başarılı.");

            // H2DB
            H2DbStarting();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Veritabanı bağlantısı başarısız!");
        }
    }

    // H2DB
    // H2 Web Konsolunu başlatmak için
    private void H2DbStarting() {
        try {
            Server server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("H2 Web Console is running at: http://localhost:8082");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // config.properties dosyasını oku
    private static void loadDatabaseConfig() {
        try (FileInputStream fis = new FileInputStream("src/main/config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);

            URL = properties.getProperty("db.url", "jdbc:h2:./h2db/user_management");
            USERNAME = properties.getProperty("db.username", "sa");
            PASSWORD = properties.getProperty("db.password", "");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Veritabanı yapılandırması yüklenemedi!");
        }
    }

    // Singleton instance
    public static synchronized SingletonPropertiesDBConnection getInstance() {
        if (instance == null) {
            instance = new SingletonPropertiesDBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Bağlantıyı kapat
    public static void closeConnection() {
        if (instance != null && instance.connection != null) {
            try {
                instance.connection.close();
                System.out.println("🔒 Veritabanı bağlantısı kapatıldı.");
            } catch (SQLException e) {
                throw new RuntimeException("❌ Bağlantı kapatılırken hata oluştu!", e);
            }
        }
    }

    // main test
    public static void main(String[] args) throws SQLException {
        //dataSet();
    }
}