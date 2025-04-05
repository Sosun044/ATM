package com.muhammedsosun.atm.dto;
import lombok.*;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class NotebookDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String category; // Örn: "Kişisel", "İş", "Okul"
    private boolean pinned;  // Sabitlenmiş not mu?
    private UserDTO userDTO; //Composition

    // Constructorlar





    // Getter ve Setter'lar


    @Override
    public String toString() {
        return "NotebookDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", category='" + category + '\'' +
                ", pinned=" + pinned +
                ", userDTO=" + userDTO +
                '}';
    }
}
