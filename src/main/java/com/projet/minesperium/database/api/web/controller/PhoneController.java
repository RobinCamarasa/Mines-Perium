package com.projet.minesperium.database.api.web.controller;

import com.projet.minesperium.database.api.dao.ScoreDao;
import com.projet.minesperium.database.api.dao.UserDao;
import com.projet.minesperium.database.api.model.User;
import com.projet.minesperium.database.api.web.dto.PhoneNumberDto;
import com.projet.minesperium.database.api.web.dto.ScoreDto;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/phonesnumber")
@Transactional

public class PhoneController {
    private final UserDao userDao;

    public PhoneController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public List<PhoneNumberDto> all() {
        List<User> users = userDao.findAll();
        List<PhoneNumberDto> phoneNumberDtos = new ArrayList<>();
        for (User user : users) {
            if(user.getActivated()) {
                phoneNumberDtos.add(new PhoneNumberDto(user));
            }
        }
        return phoneNumberDtos;
    }

}
