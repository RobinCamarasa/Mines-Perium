package com.projet.minesperium.database.api.web.controller;

import com.projet.minesperium.database.api.dao.GameDao;
import com.projet.minesperium.database.api.dao.ScoreDao;
import com.projet.minesperium.database.api.dao.UserDao;
import com.projet.minesperium.database.api.model.Score;
import com.projet.minesperium.database.api.model.User;
import com.projet.minesperium.database.api.web.dto.GameDto;
import com.projet.minesperium.database.api.web.dto.ScoreDto;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

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

}
