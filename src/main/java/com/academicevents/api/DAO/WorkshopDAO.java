package com.academicevents.api.DAO;

import com.academicevents.api.DTO.workshop.WorkshopCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkshopDAO {
    @Autowired
    static Connection conn = DB.getConnection();

    public static void createWorkshop(WorkshopCreateDTO workshop) {

    }

    public static ResultSet searchWorkshopByName(WorkshopCreateDTO workshop) {
        String query = "SELECT * FROM minicurso WHERE titulo = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshop.getTitulo());
            ResultSet result = statement.executeQuery();
            if(result.next()) {
                return result;
            }
            return null;
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkWorkshopExistsByName(WorkshopCreateDTO workshop) {
        String query = "SELECT * FROM minicurso WHERE titulo = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshop.getTitulo());
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
    }

    public static boolean saveWorkshop(WorkshopCreateDTO workshop) {
        String query = "INSERT INTO minicurso (codigo, banner, codigo_evento, titulo, descricao, datainicio, datafim, status, qtddparticipantes) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, workshop.getCodigo());
            statement.setString(2, workshop.getBanner());
            statement.setString(3, workshop.getCodigoEvento());
            statement.setString(4, workshop.getTitulo());
            statement.setString(5, workshop.getDescricao());
            statement.setObject(6, workshop.getDatainicio());
            statement.setObject(7, workshop.getDatafim());
            statement.setBoolean(8, workshop.isStatus());
            statement.setInt(9, workshop.getVagas());
            statement.execute();
            DB.closeConnection();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
