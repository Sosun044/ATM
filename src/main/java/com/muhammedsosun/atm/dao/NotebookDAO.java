package com.muhammedsosun.atm.dao;

import com.muhammedsosun.atm.dto.NotebookDTO;
import com.muhammedsosun.atm.iofiles.SpecialFileHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotebookDAO {

    // Geçici bellek listesi
    private static final List<NotebookDTO> notebookList = new ArrayList<>();
    private static Long idCounter = 1L;
    private SpecialFileHandler fileHandler;
    private static final String FILE_PATH = "notlar.txt";


    public NotebookDAO(){
        this.fileHandler = new SpecialFileHandler();
        this.fileHandler.setFilePath(FILE_PATH);
        this.fileHandler.createFileIfNotExists();
    }

    private Long generatedId() {
        return idCounter++;
    }

    public static Long getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Long idCounter) {
        NotebookDAO.idCounter = idCounter;
    }

    // Dosyaya yaz
    public void saveToFile(NotebookDTO notebook) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("notlar.txt",true))) {
            bufferedWriter.write(notebook.toString() + "\n");
            System.out.println("Dosyaya yazıldı: " + notebook.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Belleğe kaydet
    public void save(NotebookDTO notebook) {
        if (notebook.getId() == null || notebook.getId() == 0) {
            notebook.setId(generatedId());
        }
        notebookList.add(notebook);
        System.out.println("Not kaydedildi: " + notebook.getTitle());
    }

    // Tüm notları getir
    public List<NotebookDTO> findAll() {
        return new ArrayList<>(notebookList); // dışarıya kopyasını verir
    }
}