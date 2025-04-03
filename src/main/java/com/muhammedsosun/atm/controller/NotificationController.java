package com.muhammedsosun.atm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class NotificationController {

    @FXML
    private ListView<String> notificationList;

    private static List<String> notifications = new ArrayList<>();

    public static void addNotification(String message) {
        notifications.add(message);
    }

    @FXML
    public void initialize() {
        notificationList.getItems().addAll(notifications);
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) notificationList.getScene().getWindow();
        stage.close();
    }
}
