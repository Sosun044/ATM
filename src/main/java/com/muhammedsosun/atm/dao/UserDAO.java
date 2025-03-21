package com.muhammedsosun.atm.dao;

import com.muhammedsosun.atm.dto.UserDTO;

import java.util.List;
import java.util.Optional;

//USERDAO
public class UserDAO implements IDaoImplements<UserDTO>{
    //FIELD


    //CREATE
    @Override
    public Optional<UserDTO> create(UserDTO userDTO) {
        return Optional.empty();
    }
    //LIST
    @Override
    public List<UserDTO> list() {
        return List.of();
    }
    //FINDBYNAME
    @Override
    public Optional<UserDTO> findByName(String name) {
        return Optional.empty();
    }
    //FINDBYID
    @Override
    public Optional<UserDTO> findById(int id) {
        return Optional.empty();
    }
    //UPDATE
    @Override
    public Optional<UserDTO> update(int id, UserDTO userDTO) {
        return Optional.empty();
    }
    //DELETE
    @Override
    public Optional<UserDTO> delete(int id) {
        return Optional.empty();
    }
}
