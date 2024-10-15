package com.academicevents.api.handlers;

import com.academicevents.api.DAO.DB;
import com.academicevents.api.models.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class CreateUser {
    public static void saveUser(User user) {
        Connection conn = DB.getConnection();
        String userType = user.getRole().getDisplayName();
        String query = "INSERT INTO " + userType + " (cpf, nome, rua, numero, bairro, cidade, estado) " + "VALUES ( ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement =  conn.prepareStatement(query);
            statement.setString(1, user.getCpf());
            statement.setString(2, user.getName());
            statement.setString(3, user.getRua());
            statement.setString(4, user.getNumero());
            statement.setString(5, user.getBairro());
            statement.setString(6, user.getCidade());
            statement.setString(7, user.getEstado());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
