package com.academicevents.api.handlers;

import com.academicevents.api.DAO.UserDAO;
import com.academicevents.api.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@CrossOrigin(origins = "*")
public class UserHandlers {
    public static boolean saveUser(User user) {
        if(!UserDAO.searchUserByCpf(user.getCpf())){
            user.setPassword(HashPasswordHandler.hashPassword(user.getPassword()));
            return UserDAO.saveUser(user);
        } else {
            return false;
        }
    }

    public static boolean deleteUser(String cpf) {
        if (UserDAO.searchUserByCpf(cpf))    {
            return UserDAO.deleteUser(cpf);
        }
        return false;
    }
}
