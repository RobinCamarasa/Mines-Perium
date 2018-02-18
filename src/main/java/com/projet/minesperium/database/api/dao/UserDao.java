package com.projet.minesperium.database.api.dao;

import com.projet.minesperium.database.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
