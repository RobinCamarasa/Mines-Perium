package com.projet.minesperium.database.api.web.dto;

import com.projet.minesperium.database.api.model.Game;
import com.projet.minesperium.database.api.model.Score;
import com.projet.minesperium.database.api.model.User;
import com.projet.minesperium.database.api.model.Util;

import java.util.ArrayList;
import java.util.List;

public class UserCompleteDto {
    private Long id;
    private String pseudo;
    private String civilisation;
    private Float value;
    private String mail;

    public UserCompleteDto(User user) {
        this.id = user.getId();
        this.pseudo = user.getPseudo();
        this.civilisation = user.getCivilisation();
        this.value = 0f;
        this.mail = user.getMail();
    }

    public UserCompleteDto() {
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

    public Float getValue() {
        return value;
    }

    public String getMail() {
        return mail;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public static List<UserCompleteDto> getRanking(List<User> users, List<Score> scores) {
        List<UserCompleteDto> userCompleteDtos = new ArrayList<>();
        for (User user : users) {
            UserCompleteDto userCompleteDto = new UserCompleteDto(user);
            Float Tower_High_Score = 0f;
            for (Score score : scores) {
                if(user.getId().equals(score.getUser().getId()) && score.getGame().getId() != 171l) {
                    userCompleteDto.setValue(userCompleteDto.getValue() + score.getValue());
                }
                if (user.getId().equals(score.getUser().getId()) && score.getGame().getId() == 171l && score.getValue() > Tower_High_Score) {
                    Tower_High_Score = score.getValue();
                }
            }
            userCompleteDto.setValue(userCompleteDto.getValue() + Tower_High_Score);
            userCompleteDtos.add(userCompleteDto);
        }
        sort(userCompleteDtos);
        return userCompleteDtos;
    }

    public void setValue(List<Score> scores) {
        for (Score score : scores) {
            if(score.getUser().getId().equals(this.getId())) {
                value += score.getValue();
            }
        }
    }

    public static List<UserCompleteDto> getRanking(List<User> users, List<Score> scores, Game game) {
        List<UserCompleteDto> userCompleteDtos = new ArrayList<>();
        for (User user : users) {
            UserCompleteDto userCompleteDto = new UserCompleteDto(user);
            Float Tower_High_Score = 0f;
            for (Score score : scores) {
                if(user.getId().equals(score.getUser().getId()) && score.getGame().getId() != 171l && score.getGame().getId().equals(game.getId())) {
                    userCompleteDto.setValue(userCompleteDto.getValue() + score.getValue());
                }
                if (user.getId().equals(score.getUser().getId()) && score.getGame().getId() == 171l && score.getValue() > Tower_High_Score  && score.getGame().getId().equals(game.getId())) {
                    Tower_High_Score = score.getValue();
                }
            }
            userCompleteDto.setValue(userCompleteDto.getValue() + Tower_High_Score);
            userCompleteDtos.add(userCompleteDto);
        }
        sort(userCompleteDtos);
        return userCompleteDtos;
    }

    public static void sort(List<UserCompleteDto> userCompleteDtos) {
        for (int i = 0 ; i < userCompleteDtos.size() ; i++) {
            for (int j = i+1 ; j < userCompleteDtos.size() ; j++) {
                if (userCompleteDtos.get(i).getValue() < userCompleteDtos.get(j).getValue()) {
                    UserCompleteDto temp = userCompleteDtos.get(i);
                    userCompleteDtos.set(i, userCompleteDtos.get(j));
                    userCompleteDtos.set(j, temp);
                }
            }
        }
    }
}
