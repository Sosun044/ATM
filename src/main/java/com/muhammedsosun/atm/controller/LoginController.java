package com.muhammedsosun.atm.controller;


import com.muhammedsosun.atm.dao.UserDAO;
import com.muhammedsosun.atm.dto.UserDTO;
import com.muhammedsosun.atm.utils.*;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

public class LoginController {
    private UserDAO userDAO;

    public LoginController() {
        userDAO = new UserDAO();
    }

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
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        Optional<UserDTO> optionalLoginUserDTO = userDAO.loginUser(username, password);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        if (optionalLoginUserDTO.isPresent()) {
            UserDTO userDTO = optionalLoginUserDTO.get();
            showAlert("Başarılı", "Giriş Başarılı: " + userDTO.getUsername(), Alert.AlertType.INFORMATION);

            // 🔥 Kullanıcıyı SessionManager'a Set Et
            SessionManager.setCurrentUser(userDTO);

            // 🔍 Veritabanından Tam Profili Al
            Optional<UserDTO> fullUserProfile = userDAO.findById(userDTO.getId());

            fullUserProfile.ifPresent(freshUser -> {
                userDTO.setEmail(freshUser.getEmail());
                userDTO.setRole(freshUser.getRole());
            });

            // 🔥 Güncellenmiş Kullanıcıyı Tekrar Set Et
            SessionManager.setCurrentUser(userDTO);

            // 📢 Bildirim ekle (Doğru yerde!)
            NotificationController.addNotification("✅ " + userDTO.getUsername() + " giriş yaptı!"+ "   Giriş Yapıldıgı tarih:  " +  formatter.format(new Date()));


            // 🎯 Kullanıcı Yönlendirmesi
            if (userDTO.getRole() == ERole.ADMIN) {
                openAdminPane(userDTO);
            } else {
                openUserHomePane();
            }

        } else {
            showAlert("Başarısız", "Giriş bilgileri hatalı", Alert.AlertType.ERROR);
            NotificationController.addNotification("Hatalı giriş yapıldı!" +" Tarih:  " + formatter.format(new Date()));
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


    private void openAdminPane(UserDTO user) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLPath.ADMIN));
            Parent parent = fxmlLoader.load();

            AdminController controller = fxmlLoader.getController();
            controller.setUser(user); // ✅ artık null değil

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

    @FXML
    private void switchToRegister(ActionEvent actionEvent) {
        try {
            // 1.YOL
            /*
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXMLPath.REGISTER));
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.setTitle("Kayıt Ol");
            stage.show();
             */
            // 2.YOL
            SceneHelper.switchScene(FXMLPath.REGISTER, usernameField, "Kayıt Ol");
        } catch (Exception e) {
            System.out.println(SpecialColor.RED + "Register Sayfasına yönlendirme başarısız" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Hata", "Kayıt ekranı yüklenemedi", Alert.AlertType.ERROR);
        }
    }
} //end LoginController