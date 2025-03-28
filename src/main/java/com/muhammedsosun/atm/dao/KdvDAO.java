package com.muhammedsosun.atm.dao;

import com.muhammedsosun.atm.database.SingletonPropertiesDBConnection;
import com.muhammedsosun.atm.dto.KdvDTO;
import com.muhammedsosun.atm.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KdvDAO implements IDaoImplements<KdvDTO>{
    private Connection connection;
    public KdvDAO(){
        this.connection = SingletonPropertiesDBConnection.getInstance().getConnection();
    }
    private Integer id;
    private Double amount;
    private Double kdvAmount;
    private Double kdvRate;
    private Double totalAmount;
    private String receiptNumber;
    private LocalDate transactionDate;
    private String description;
    private String exportFormat;
    @Override
    public Optional<KdvDTO> create(KdvDTO kdvDTO) {
        String sql = "INSERT INTO kdv_table(amount,kdvAmount,kdvRate,totalAmount,receiptNumber,transactionDate,description,exportFormat) VALUES(?,?,?,?,?,?,?,?)";
        kdvDTO.calculateTotals();
        try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1,kdvDTO.getAmount());
            ps.setDouble(2,kdvDTO.getKdvAmount());
            ps.setDouble(3,kdvDTO.getKdvRate());
            ps.setDouble(4,kdvDTO.getTotalAmount());
            ps.setString(5,kdvDTO.getReceiptNumber());
            ps.setDate(6,Date.valueOf(kdvDTO.getTransactionDate()));
            ps.setString(7,kdvDTO.getDescription());
            ps.setString(8,kdvDTO.getExportFormat());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        kdvDTO.setId(rs.getInt(1));
                        return Optional.of(kdvDTO);
                    }
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public Optional<List<KdvDTO>> list() {
        List<KdvDTO> list = new ArrayList<>();
        String sql = "Select * from kdv_table ORDER BY transactionDate DESC";
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(mapToObjectDTO(rs));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public Optional<KdvDTO> findByName(String receiptNumber){
        String sql = "Select * from kdv_table WHERE receiptNumber = ?";
        return selectSingle(sql,receiptNumber);
    }
    @Override
    public Optional<KdvDTO> findById(int id) {
        String sql = "Select * from kdv_table WHERE id = ?";
        return selectSingle(sql,id);
    }
    @Override
    public Optional<KdvDTO> update(int id, KdvDTO updated) {
        Optional<KdvDTO> existing = findById(id);
        if (existing.isPresent()){
            String sql = "UPDATE kdv_table SET amount=?, kdvRate=?, kdvAmount=?, totalAmount=?, receiptNumber=?, transactionDate=?, description=?, exportFormat=? WHERE id=?";
            updated.calculateTotals();
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setDouble(1,updated.getAmount());
                ps.setDouble(2,updated.getKdvAmount());
                ps.setDouble(3,updated.getKdvRate());
                ps.setDouble(4,updated.getTotalAmount());
                ps.setString(5,updated.getReceiptNumber());
                ps.setDate(6,Date.valueOf(updated.getTransactionDate()));
                ps.setString(7,updated.getDescription());
                ps.setString(8,updated.getExportFormat());
                int affected = ps.executeUpdate();
                if (affected > 0) {
                    updated.setId(id);
                    return Optional.of(updated);
                }

            }catch (Exception e) {
                e.printStackTrace();
            }

        }
        return Optional.empty();
    }
    @Override
    public Optional<KdvDTO> delete(int id) {
        Optional<KdvDTO> existing = findById(id);
        if (existing.isPresent()) {
            String sql = "DELETE FROM kdv_table WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                int affected = ps.executeUpdate();
                if (affected > 0) return existing;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
    @Override
    public KdvDTO mapToObjectDTO(ResultSet rs) throws SQLException {
        return KdvDTO.builder()
                .id(rs.getInt("id"))
                .amount(rs.getDouble("amount"))
                .kdvRate(rs.getDouble("kdvRate"))
                .kdvAmount(rs.getDouble("kdvAmount"))
                .totalAmount(rs.getDouble("totalAmount"))
                .receiptNumber(rs.getString("receiptNumber"))
                .transactionDate(rs.getDate("transactionDate").toLocalDate())
                .description(rs.getString("description"))
                .exportFormat(rs.getString("exportFormat"))
                .build();
    }
    @Override
    public Optional<KdvDTO> selectSingle(String sql, Object... params) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapToObjectDTO(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
