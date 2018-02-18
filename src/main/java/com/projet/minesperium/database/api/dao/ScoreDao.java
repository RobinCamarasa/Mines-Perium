package com.projet.minesperium.database.api.dao;

import com.projet.minesperium.database.api.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreDao extends JpaRepository<Score, Long> {
}
