package com.muhammedsosun.atm.controller;


import com.muhammedsosun.atm.dao.UserDAO;
import com.muhammedsosun.atm.dto.UserDTO;
import com.muhammedsosun.atm.utils.ERole;
import com.muhammedsosun.atm.utils.FXMLPath;
import com.muhammedsosun.atm.utils.SceneHelper;
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

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void specialOnEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    @FXML
    public void login() {

        //
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        Optional<UserDTO> optionalLoginUserDTO = userDAO.loginUser(username, password);

        if (optionalLoginUserDTO.isPresent()) {
            UserDTO userDTO = optionalLoginUserDTO.get();
            showAlert("Başarılı", "Giriş Başarılı: " + userDTO.getUsername(), Alert.AlertType.INFORMATION);

            if (userDTO.getRole() == ERole.ADMIN) {
                openAdminPane();
            } else {
                openUserHomePane();
            }


        } else {
            showAlert("Başarısız", "Giriş bilgileri hatalı", Alert.AlertType.ERROR);
        }
    }
    private void openUserHomePane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLPath.USER_HOME));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.setTitle("Kullanıcı Paneli");
            stage.show();
        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "Kullanıcı paneline yönlendirme başarısız" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Hata", "Kullanıcı ekranı yüklenemedi", Alert.AlertType.ERROR);
        }
    }
    private void openAdminPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLPath.ADMIN));
            Parent parent = fxmlLoader.load();

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.setTitle("Admin Panel");
            stage.show();
        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "Admin Sayfasına yönlendirme başarısız" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Hata", "Admin ekranı yüklenemedi", Alert.AlertType.ERROR);
        }
    }

    /// //////////////////////////////////////////////////////////////////////////////////////
    // Sayfalar Arasında Geçiş (LOGIN -> REGISTER)
    // Register (Switch)
    @FXML
    private void switchToRegister(ActionEvent actionEvent) {
        try {
            SceneHelper.switchScene(FXMLPath.REGISTER, usernameField, "Kayıt Ol");
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println(SpecialColor.RED + "Register Sayfasında yönlendirilmedi" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Hata", "Kayıt Ekranı Yüklenemedi", Alert.AlertType.ERROR);
        }
    } //end switchToLogin

} //end LoginController