package com.projet.minesperium.database.api.model;

import javax.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Float value;

    @OneToOne
    private User user;

    @OneToOne
    private Game game;

    public Score(Float value, User user, Game game) {
        this.value = value;
        this.user = user;
        this.game = game;
    }

    public Score() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                ", user=" + user +
                ", game=" + game +
                '}';
    }
}
