package com.academicevents.api.models;

import com.academicevents.api.enums.ROLES;

public class Administrator extends User {
    public Administrator(
            String name,
            String email,
            String password,
            String cpf,
            String rua,
            String numero,
            String bairro,
            String cidade,
            String estado,
            ROLES role) {
        super(name, email, password, cpf, rua, numero, bairro, cidade, estado, role);
    }
}