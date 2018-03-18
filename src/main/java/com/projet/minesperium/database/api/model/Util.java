package com.projet.minesperium.database.api.model;

import com.projet.minesperium.database.api.web.dto.UserCompleteDto;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public Util() {
    }

    public static List<Score> getHighScore(List<Score> scores) {
        Util.sort(scores);
        Score score = scores.get(0);
        List<Score> scores1 = new ArrayList<>();
        for (Score score1 : scores) {
            if (score.getGame().getId() != score1.getGame().getId()) {
                scores1.add(score);
            }
            score = score1;
        }
        scores1.add(scores.get(scores.size() - 1));
        System.out.println("\n\n" + scores + "\n\n");
        return scores1;
    }

    public static void sort(List<Score> scores) {
        for (int i = 0 ; i < scores.size() ; i++) {
            for (int j = i+1 ; j < scores.size() ; j++) {
                if (        scores.get(i).getUser().getId() > scores.get(j).getUser().getId()
                        || (scores.get(i).getUser().getId() == scores.get(j).getUser().getId() &&
                            scores.get(i).getGame().getId() > scores.get(j).getGame().getId())
                        || (scores.get(i).getUser().getId() == scores.get(j).getUser().getId() &&
                            scores.get(i).getGame().getId() == scores.get(j).getGame().getId() &&
                            scores.get(i).getValue() > scores.get(j).getValue())) {
                    Score temp = scores.get(i);
                    scores.set(i, scores.get(j));
                    scores.set(j, temp);
                }
            }
        }
    }
}
