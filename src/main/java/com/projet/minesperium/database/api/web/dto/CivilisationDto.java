package com.projet.minesperium.database.api.web.dto;

import com.projet.minesperium.database.api.model.Game;
import com.projet.minesperium.database.api.model.Score;
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

    public static List<CivilisationDto> getRanking(List<Score> scores) {
//        scores = Util.getHighScore(scores);
        List<CivilisationDto> civilisationDtos = new ArrayList<>();
        civilisationDtos.add(new CivilisationDto("Egyptienne"));
        civilisationDtos.add(new CivilisationDto("Gauloise"));
        civilisationDtos.add(new CivilisationDto("Romaine"));
        civilisationDtos.add(new CivilisationDto("Viking"));
        for (Score score : scores) {
            for (int i = 0 ; i < 4 ; i++) {
                if (score.getUser().getCivilisation().equalsIgnoreCase(civilisationDtos.get(i).getName())) {
                    civilisationDtos.get(i).addScore(score.getValue());
                }
            }
        }
        sort(civilisationDtos);
        return civilisationDtos;
    }

    public static List<CivilisationDto> getRanking(List<Score> scores, Game game) {
//        scores = Util.getHighScore(scores);
        List<CivilisationDto> civilisationDtos = new ArrayList<>();
        civilisationDtos.add(new CivilisationDto("Egyptienne"));
        civilisationDtos.add(new CivilisationDto("Gauloise"));
        civilisationDtos.add(new CivilisationDto("Romaine"));
        civilisationDtos.add(new CivilisationDto("Viking"));
        for (Score score : scores) {
            for (int i = 0 ; i < 4 ; i++) {
                if (score.getUser().getCivilisation().equalsIgnoreCase(civilisationDtos.get(i).getName()) && game.getId().equals(score.getGame().getId())) {
                    civilisationDtos.get(i).addScore(score.getValue());
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
