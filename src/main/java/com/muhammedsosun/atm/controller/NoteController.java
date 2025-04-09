package com.muhammedsosun.atm.controller;

import com.muhammedsosun.atm.dao.NotebookDAO;
import com.muhammedsosun.atm.dto.NotebookDTO;
import com.muhammedsosun.atm.dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoteController {

    @FXML
    public TextField titleField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    public TextArea contentArea;

    @FXML
    public CheckBox pinnedCheckBox;

    //Benim notlarım kısmı
    @FXML
    private ListView<String> notesList;

    private static List<String> myNotes = new ArrayList<>();


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
                .category(categoryComboBox.getValue())
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