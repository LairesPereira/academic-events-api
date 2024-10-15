package com.academicevents.api.handlers;

import com.academicevents.api.DAO.DB;
import com.academicevents.api.models.User;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CreateUser {
    public static ResultSet saveUser(User user) {
        Connection conn = DB.getConnection();

        String query = "select * from administrador LIMIT 1";
        try {
            PreparedStatement st =  conn.prepareStatement(query);
            return st.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
