package com.projet.minesperium.database.api.web.controller;

import com.projet.minesperium.database.api.dao.GameDao;
import com.projet.minesperium.database.api.dao.ScoreDao;
import com.projet.minesperium.database.api.dao.UserDao;
import com.projet.minesperium.database.api.model.Game;
import com.projet.minesperium.database.api.model.Score;
import com.projet.minesperium.database.api.model.User;
import com.projet.minesperium.database.api.web.dto.GameDto;
import com.projet.minesperium.database.api.web.dto.ScoreDto;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/scores")
@Transactional

public class ScoreController {
    private final ScoreDao scoreDao;
    private final UserDao userDao;
    private final GameDao gameDao;

    public ScoreController(ScoreDao scoreDao, UserDao userDao, GameDao gameDao) {
        this.scoreDao = scoreDao;
        this.userDao = userDao;
        this.gameDao = gameDao;
    }

    @GetMapping
    public List<ScoreDto> getAll() {
        return scoreDao.findAll().stream().map(ScoreDto::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/add/{id_user}/{id_game}/{value}")
    public ScoreDto add(@PathVariable Long id_user, @PathVariable Long id_game, @PathVariable Float value) {
        Score score = new Score(value, userDao.findOne(id_user), gameDao.findOne(id_game));
        scoreDao.save(score);
        return new ScoreDto(score);
    }

    @PostMapping(value = "/addscore/{user}/{game}/{value}")
    public ScoreDto addscore(@PathVariable String user, @PathVariable String game, @PathVariable Float value) {
        List<User> users = userDao.findAll();
        List<Game> games = gameDao.findAll();
        User user_final = null;
        Game game_final = null;
        for (User user1 : users) {
            if(user1.getPseudo().equalsIgnoreCase(user)) {
                user_final = user1;
            }
        }
        for (Game game1 : games) {
            if (game1.getName().equalsIgnoreCase(game)) {
                game_final = game1;
            }
        }
        if(user_final != null && game_final != null) {
            Score score = new Score(value, user_final, game_final);
            scoreDao.save(score);
            return new ScoreDto(score);
        } else {
            return new ScoreDto();
        }

    }

}
