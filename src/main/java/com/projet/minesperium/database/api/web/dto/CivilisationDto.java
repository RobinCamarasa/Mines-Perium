package com.projet.minesperium.database.api.web.dto;

import com.projet.minesperium.database.api.model.Game;
import com.projet.minesperium.database.api.model.Score;
import com.projet.minesperium.database.api.model.User;
import com.projet.minesperium.database.api.model.Util;

import java.util.ArrayList;
import java.util.List;

public class CivilisationDto {
    private String name;
    private Float score;

    public CivilisationDto(String name) {
        this.name = name;
        this.score = 0f;
    }

    public String getName() {
        return name;
    }

    public Float getScore() {
        return score;
    }

    public void addScore(Float score) {
        this.score += score;
    }

    public static List<CivilisationDto> getRanking(List<Score> scores, List<User> users) {
        List<UserCompleteDto> userCompleteDtos = UserCompleteDto.getRanking(users, scores);
        List<CivilisationDto> civilisationDtos = new ArrayList<>();
        civilisationDtos.add(new CivilisationDto("Egyptienne"));
        civilisationDtos.add(new CivilisationDto("Gauloise"));
        civilisationDtos.add(new CivilisationDto("Romaine"));
        civilisationDtos.add(new CivilisationDto("Viking"));
        for (UserCompleteDto userCompleteDto : userCompleteDtos) {
            for (int i = 0; i < 4; i++) {
                if (civilisationDtos.get(i).getName().equalsIgnoreCase(userCompleteDto.getCivilisation())) {
                    civilisationDtos.get(i).addScore(userCompleteDto.getValue());
                }
            }
        }
        sort(civilisationDtos);
        return civilisationDtos;
    }

    public static List<CivilisationDto> getRanking(List<Score> scores, List<User> users, Game game) {
        List<UserCompleteDto> userCompleteDtos = UserCompleteDto.getRanking(users, scores, game);
        List<CivilisationDto> civilisationDtos = new ArrayList<>();
        civilisationDtos.add(new CivilisationDto("Egyptienne"));
        civilisationDtos.add(new CivilisationDto("Gauloise"));
        civilisationDtos.add(new CivilisationDto("Romaine"));
        civilisationDtos.add(new CivilisationDto("Viking"));
        for (UserCompleteDto userCompleteDto : userCompleteDtos) {
            for (int i = 0; i < 4; i++) {
                if (civilisationDtos.get(i).getName().equalsIgnoreCase(userCompleteDto.getCivilisation())) {
                    civilisationDtos.get(i).addScore(userCompleteDto.getValue());
                }
            }
        }
        sort(civilisationDtos);
        return civilisationDtos;
    }

    public static void sort(List<CivilisationDto> civilisationDtos) {
        for (int i = 0 ; i < civilisationDtos.size() ; i++) {
            for (int j = i+1 ; j < civilisationDtos.size() ; j++) {
                if (civilisationDtos.get(i).getScore() < civilisationDtos.get(j).getScore()) {
                    CivilisationDto temp = civilisationDtos.get(i);
                    civilisationDtos.set(i, civilisationDtos.get(j));
                    civilisationDtos.set(j, temp);
                }
            }
        }
    }
}
