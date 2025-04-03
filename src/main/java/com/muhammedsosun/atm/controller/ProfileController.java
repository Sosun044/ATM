package com.muhammedsosun.atm.controller;

import com.muhammedsosun.atm.dao.UserDAO;
import com.muhammedsosun.atm.dto.UserDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Optional;

public class ProfileController {

    private  final UserDAO userDAO = new UserDAO();

    @FXML private Label username;
    @FXML private Label email;
    @FXML private  Label role;

    public void setUser(UserDTO user) {
        // Veritabanından güncel halini alalım
        System.out.println("👤 ProfileController#setUser gelen user: " + user);
        Optional<UserDTO> dbUser = getUserProfile(user.getId());

        if (dbUser.isPresent()) {
            UserDTO fresh = dbUser.get();
            username.setText(fresh.getUsername());
            email.setText(fresh.getEmail());
            role.setText(fresh.getRole().toString());
        } else {
            username.setText(user.getUsername());
            email.setText(user.getEmail());
            role.setText(user.getRole().toString());
        }
    }

    public Optional<UserDTO> getUserProfile(int requestedUserId) {
        return userDAO.findById(requestedUserId);
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();
    }
}
