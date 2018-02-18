package com.projet.minesperium.database.api.web.controller;

import com.projet.minesperium.database.api.dao.GameDao;
import com.projet.minesperium.database.api.model.Game;
import com.projet.minesperium.database.api.model.Score;
import com.projet.minesperium.database.api.web.dto.GameDto;
import com.projet.minesperium.database.api.web.dto.ScoreDto;
import com.projet.minesperium.database.api.web.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/games")
@Transactional

public class GameController {
    private final GameDao gameDao;

    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @GetMapping
    public List<GameDto> getAll() {
        return gameDao.findAll().stream().map(GameDto::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/new")
    public GameDto addGame(@RequestBody Game game) {
        gameDao.save(game);
        return (new GameDto(game));
    }

}
