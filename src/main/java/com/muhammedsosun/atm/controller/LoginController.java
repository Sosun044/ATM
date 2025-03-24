package com.muhammedsosun.atm.controller;


import com.muhammedsosun.atm.dao.UserDAO;
import com.muhammedsosun.atm.dto.UserDTO;
import com.muhammedsosun.atm.utils.SpecialColor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Optional;

// Java ile XML arasında Köprü görür.
public class LoginController {

    // Injection
    // Veri tabanı işlemleri için)
    private UserDAO userDAO;

    // Parametresiz Constructor
    public LoginController() {
        userDAO = new UserDAO();
    }

    /// /////////////////////////////////////////////////////////////////////////////
    ///  FXML Field
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    /// //////////////////////////////////////////////////////////////////////////////////////
    // ShowAlert (Kullanıcıya bilgi veya hata mesajları göstermek için kullanılan yardımcı metot)
    // INFORMATION:BILGI,  ERROR: HATA
    private void showAlert(String title, String message, Alert.AlertType type) {
        // Alert nesnesi oluşturuyoruz ve parametre olarak alınan başlık, mesaj ve tipi ayarlıyoruz
        Alert alert = new Alert(type);

        // Title
        alert.setTitle(title);

        // Message
        alert.setContentText(message);

        // Alert penceresini gösteriyoruz ve kullanıcıdan bir yanıt bekliyoruz
        alert.showAndWait();
    } //end showAlert

    /// //////////////////////////////////////////////////////////////////////////////////////
    // Klavyeden ENTER tuşuna bastığımda giriş yapsın
    @FXML
    private void specialOnEnterPressed(KeyEvent keyEvent) {
        // Eğer basılan tuş ENTER ise
        if (keyEvent.getCode() == KeyCode.ENTER) {
            // Eğer Enter'a basarsam login() sayfasına gitsin
            login();
        }
    } // onEnterPressed

    /// /////////////////////////////////////////////////////////////////////////////////////
    // Login ( Kullanıcı giriş işlemini gerçekleştiren metot)
    @FXML
    public void login() {
        // Kullanıcı Giriş Yaparken Username, Password Almak
        String username, password;
        username = usernameField.getText();
        password = passwordField.getText();

        // optionalUserDTO(Veri tabanına ekle)
        Optional<UserDTO> optionalUserDTO = userDAO.loginUser(username, password);

        // Eğer Veri Boş değilse
        if (optionalUserDTO.isPresent()) {
            // Veriyi almak
            UserDTO userDTO = optionalUserDTO.get();

            // Eğer başarılıysa Pop-up göster
            showAlert("Başarılı", "Giriş Başarılı", Alert.AlertType.INFORMATION);

            // Kayıt başarılı ise Admin Panelkine geçiş sağla
            openAdminPane();

        } else {
            // Eğer bilgiler yanlışsa, database kayıt olmamışsa
            showAlert("Başarılı", "Giriş Başarılı", Alert.AlertType.ERROR);
        }
    }

    /// //////////////////////////////////////////////////////////////////////////////////////
    // Eğer Login başarılıysa Admin Panel(Dashboard)
    private void openAdminPane() {
        try {
            // FXML Dosyalarını Yükle (Kayıt ekranının FXML dosyasını yüklüyoruz)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hamitmizrak/ibb_ecodation_javafx/view/admin.fxml"));
            Parent parent = fxmlLoader.load();

            // Var olan sahneyi alıp ve değiştirmek ve
            // Admin sayfasına Veri gönderelim.
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));

            // Pencere başlığını 'Admin Panel' olarak ayarlıyalım
            stage.setTitle("Admin Panel: "+usernameField);

            // Sahneyi göster
            stage.show();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println(SpecialColor.RED + "Admin Sayfasında yönlendirilmedi" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Hata", "Admin Ekranı Yüklenemedi", Alert.AlertType.ERROR);
        }
    }

    /// //////////////////////////////////////////////////////////////////////////////////////
    // Sayfalar Arasında Geçiş (LOGIN -> REGISTER)
    // Register (Switch)
    @FXML
    private void switchToRegister(ActionEvent actionEvent) {
        try {
            // FXML Dosyalarını Yükle (Kayıt ekranının FXML dosyasını yüklüyoruz)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/hamitmizrak/ibb_ecodation_javafx/view/register.fxml"));
            Parent parent = fxmlLoader.load();

            // Var olan sahneyi alıp ve değiştirmek
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));

            // Pencere başlığını 'Kayıt Ol' olarak ayarlıyalım
            stage.setTitle("Kayıt Ol");

            // Sahneyi göster
            stage.show();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println(SpecialColor.RED + "Register Sayfasında yönlendirilmedi" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Hata", "Kayıt Ekranı Yüklenemedi", Alert.AlertType.ERROR);
        }
    } //end switchToLogin

} //end LoginController