package com.projet.minesperium.database.api.web.dto;

import com.projet.minesperium.database.api.model.Game;

public class GameDto {
    private Long id;
    private String name;

    public GameDto() {
    }

    public GameDto(Game game) {
        this.id = game.getId();
        this.name = game.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
