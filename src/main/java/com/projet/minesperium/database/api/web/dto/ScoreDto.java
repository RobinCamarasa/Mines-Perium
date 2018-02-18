package com.projet.minesperium.database.api.web.dto;

import com.projet.minesperium.database.api.model.Score;

public class ScoreDto {
    private Long id_user;
    private String pseudo_user;
    private Long id_game;
    private String name_game;
    private Float value;

    public ScoreDto(Score score) {
        this.id_user = score.getUser().getId();
        this.pseudo_user = score.getUser().getPseudo();
        this.id_game = score.getGame().getId();
        this.name_game = score.getGame().getName();
        this.value = score.getValue();
    }

    public Long getId_user() {
        return id_user;
    }

    public String getPseudo_user() {
        return pseudo_user;
    }

    public Long getId_game() {
        return id_game;
    }

    public String getName_game() {
        return name_game;
    }

    public Float getValue() {
        return value;
    }
}
