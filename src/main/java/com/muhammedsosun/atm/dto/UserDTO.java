package com.muhammedsosun.atm.dto;

import com.muhammedsosun.atm.utils.ERole;
import lombok.*;

//@AllArgsConstructor//parametreli Construcutor
@NoArgsConstructor//parametresiz Construcutor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private ERole role;

    //parametresiz Construcutor
    //parametreli Construcutor

    public UserDTO(Integer id, String username, String password, String email, ERole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //Getter and Setter
    //method
    /*
    public static void main(String[] args) {
        UserDTO userDTO = UserDTO.builder().
        id(0)
        .username("Muhammed")
        .email("muhammedsosun06@gmail.com")
        .password("123456").
        build();
        System.out.println(userDTO);
    }
     */
}// end Class
