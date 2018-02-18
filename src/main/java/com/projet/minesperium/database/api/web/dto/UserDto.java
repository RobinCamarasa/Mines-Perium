package com.projet.minesperium.database.api.web.dto;

import com.projet.minesperium.database.api.model.User;

public class UserDto {
    private Long id;
    private String pseudo;
    private String civilisation;

    public UserDto(User user) {
        this.id = user.getId();
        this.pseudo = user.getPseudo();
        this.civilisation = user.getCivilisation();
    }

    public Long getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getCivilisation() {
        return civilisation;
    }
}
