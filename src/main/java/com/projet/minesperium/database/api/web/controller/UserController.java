package com.projet.minesperium.database.api.web.controller;

import com.projet.minesperium.database.api.dao.GameDao;
import com.projet.minesperium.database.api.dao.ScoreDao;
import com.projet.minesperium.database.api.dao.UserDao;
import com.projet.minesperium.database.api.model.User;
import com.projet.minesperium.database.api.web.dto.CivilisationDto;
import com.projet.minesperium.database.api.web.dto.UserCompleteDto;
import com.projet.minesperium.database.api.web.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Transactional

public class UserController {
    private final UserDao userDao;
    private final ScoreDao scoreDao;
    private final GameDao gameDao;

    public UserController(UserDao userDao, ScoreDao scoreDao, GameDao gameDao) {
        this.userDao = userDao;
        this.scoreDao = scoreDao;
        this.gameDao = gameDao;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userDao.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @GetMapping (value = "/ranking")
    public List<UserCompleteDto> ranking() {
        return UserCompleteDto.getRanking(userDao.findAll(), scoreDao.findAll());
    }

    @GetMapping (value = "/ranking/game/{id}")
    public List<UserCompleteDto> ranking(@PathVariable Long id) {
        return UserCompleteDto.getRanking(userDao.findAll(), scoreDao.findAll(), gameDao.getOne(id));
    }

    @GetMapping (value = "/civilisationranking")
    public List<CivilisationDto> civilisationRanking() {
        return CivilisationDto.getRanking(scoreDao.findAll());
    }

    @GetMapping (value = "/civilisationranking/game/{id}")
    public List<CivilisationDto> civilisationRanking(@PathVariable Long id) {
        return CivilisationDto.getRanking(scoreDao.findAll(), gameDao.findOne(id));
    }

    @PostMapping(value = "/new")
    public UserDto addUser(@RequestBody User user) {
        userDao.save(user);
        return new UserDto(user);
    }

    @GetMapping(value = "/identification/{pseudo}/{password}")
    public UserCompleteDto identificate(@PathVariable String pseudo, @PathVariable String password) {
        List<User> users = userDao.findAll();
        for (User user1 : users) {
            if (user1.getPassword().equalsIgnoreCase(password) && user1.getPseudo().equalsIgnoreCase(pseudo)) {
                UserCompleteDto userCompleteDto = new UserCompleteDto(user1);
                userCompleteDto.setValue(scoreDao.findAll());
                return userCompleteDto;
            }
        }
        return new UserCompleteDto();
    }

    @GetMapping(value = "/check/{pseudo}")
    public UserCompleteDto check(@PathVariable String pseudo) {
        List<User> users = userDao.findAll();
        for (User user1 : users) {
            if (user1.getPseudo().equalsIgnoreCase(pseudo)) {
                UserCompleteDto userCompleteDto = new UserCompleteDto(user1);
                userCompleteDto.setValue(scoreDao.findAll());
                return userCompleteDto;
            }
        }
        return new UserCompleteDto();
    }
}
