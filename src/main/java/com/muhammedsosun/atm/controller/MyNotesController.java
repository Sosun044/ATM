package com.muhammedsosun.atm.controller;

import com.muhammedsosun.atm.iofiles.SpecialFileHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyNotesController {
    //Benim notlarım kısmı
    private SpecialFileHandler specialFileHandler;

    @FXML
    private ListView<String> noteList;


    private static List<String> notes = new ArrayList<>();

    public MyNotesController() {
        this.specialFileHandler = new SpecialFileHandler();

        this.specialFileHandler.readFile(this.specialFileHandler.getFilePath());
    }

    public static void addNotes(String message) {
        notes.add(message);
    }

    @FXML
    public void initialize() {
        noteList.getItems().clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("notlar.txt"))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                noteList.getItems().add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void readSpecialNotes(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("notlar.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                notes.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) noteList.getScene().getWindow();
        stage.close();
    }


}
