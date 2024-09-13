package com.example.tennis_scoreboard.dao;


import java.util.Optional;

public interface Dao<T> {

    T add(T entity);

    Optional<T> findByName(String playerName);
}
