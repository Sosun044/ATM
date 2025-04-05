package com.muhammedsosun.atm.controller;

import com.muhammedsosun.atm.dao.NotebookDAO;
import com.muhammedsosun.atm.dto.NotebookDTO;
import com.muhammedsosun.atm.dto.UserDTO;
import com.muhammedsosun.atm.utils.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class NoteController {

    @FXML
    public TextField titleField;

    @FXML
    public TextArea contentArea;

    @FXML
    public CheckBox pinnedCheckBox;

    @FXML
    public Button btnSaveNote;

    private NotebookDTO createdNote;

    private NotebookDAO notebookDAO = new NotebookDAO();

    public void setCreatedNote(NotebookDTO createdNote) {
        this.createdNote = createdNote;
    }

    public NotebookDTO getCreatedNote() {
        return createdNote;
    }

    // Notu kaydetme işlemi
    @FXML
    private void saveNote(ActionEvent event) {
        System.out.println("Kaydet butonuna tıklandı!");

        createdNote = NotebookDTO.builder()
                .title(titleField.getText())
                .content(contentArea.getText())
                .pinned(pinnedCheckBox.isSelected())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        System.out.println("Note saved");
        // Pencereyi kapat
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    // Not oluşturma formunu açan metot



    public void setUser(UserDTO currentUser) {
        createdNote.setUserDTO(currentUser);
    }
}