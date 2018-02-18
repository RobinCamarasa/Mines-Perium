package com.projet.minesperium.database.api.dao;

import com.projet.minesperium.database.api.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDao extends JpaRepository<Game, Long> {
}
