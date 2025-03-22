package com.muhammedsosun.atm.dao;

import com.muhammedsosun.atm.dto.UserDTO;
import com.muhammedsosun.atm.utils.SpecialColor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//USERDAO
public class UserDAO implements IDaoImplements<UserDTO>{
    private Connection connection;
    //FIELD


    //CREATE
    @Override
    public Optional<UserDTO> create(UserDTO userDTO) {
        String sql = "INSERT INTO users(username,password,email) VALUES(?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,userDTO.getUsername());
            preparedStatement.setString(2,userDTO.getPassword());
            preparedStatement.setString(3,userDTO.getEmail());
            ///////////////////////////////
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0){
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()){
                        userDTO.setId(generatedKeys.getInt(1));
                        return Optional.of(userDTO);
                    }
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return Optional.empty();
    }
    //LIST
    @Override
    public Optional<List<UserDTO>> list() {
        List<UserDTO> userDTOList = new ArrayList<>();
        String sql  ="Select * FROM users";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()){
                userDTOList.add(UserDTO.builder()
                        .id(resultSet.getInt("id")).
                        username(resultSet.getString("username")).
                        email(resultSet.getString("email")).
                        password(resultSet.getString("password"))

                        .build());
            }
            return userDTOList.isEmpty() ? Optional.empty() : Optional.of(userDTOList);

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return Optional.empty();
    }
    //FINDBYNAME
    @Override
    public Optional<UserDTO> findByName(String name) {
        //String sql  ="Select * FROM users WHERE username=?";
        String sql  ="Select * FROM users WHERE email=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet.next()){
                UserDTO userDTO = UserDTO.builder()
                        .id(resultSet.getInt("id"))
                        .username(resultSet.getString("username"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
                return Optional.of(userDTO);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

        return Optional.empty();
    }
    //FINDBYID
    @Override
    public Optional<UserDTO> findById(int id) {
        String sql  ="Select * FROM users WHERE id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet.next()){
                UserDTO userDTO = UserDTO.builder()
                        .id(resultSet.getInt("id"))
                        .username(resultSet.getString("username"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
                return Optional.of(userDTO);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
        System.out.println(SpecialColor.GREEN+"Aradığınız " + id + " Bulunamadı"+ SpecialColor.RESET);
        return Optional.empty();
    }
    //UPDATE
    @Override
    public Optional<UserDTO> update(int id, UserDTO userDTO) {

        Optional<UserDTO> optionalUpdate = findById(id);
        if (optionalUpdate.isPresent()){
            String sql = "UPDATE users SET username=?, password=?,email=? WHERE id=?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1,userDTO.getUsername());
                preparedStatement.setString(2,userDTO.getPassword());
                preparedStatement.setString(3,userDTO.getEmail());
                preparedStatement.setInt(4,userDTO.getId());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0){
                    userDTO.setId(id);
                    return Optional.of(userDTO);
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }


        return Optional.empty();
    }
    //DELETE
    @Override
    public Optional<UserDTO> delete(int id) {
        Optional<UserDTO> optionalDelete = findById(id);
        if (optionalDelete.isPresent()){
            String sql = "DELETE FROM users  WHERE id=?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1,id);
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0){

                    return optionalDelete;
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return Optional.empty();
    }
}
