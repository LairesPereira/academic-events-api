package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@Service
@CrossOrigin(origins = "*")
public class UserHandlers {
    public static ResponseEntity<?> saveUser(User user) {
        Map<String, String> response = new HashMap<>();

        if(!UserDAO.searchUserByCpf(user.getCpf()) && UserDAO.saveUser(user)) {
            user.setPassword(HashPasswordHandler.hashPassword(user.getPassword()));
            response.put("success", "Usuaário criado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("error", "Usuaário já existente");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public static ResponseEntity<?> deleteUser(String cpf) {
        Map<String, String> response = new HashMap<>();
        if (UserDAO.searchUserByCpf(cpf) && UserDAO.deleteUser(cpf))    {
            response.put("success", "Usuário deletado com sucesso!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("error", "Erro ao deletar o usuário");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
