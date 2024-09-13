package com.example.tennis_scoreboard.dao;

import com.example.tennis_scoreboard.entity.Player;
import com.example.tennis_scoreboard.exception.EntitiesException;
import com.example.tennis_scoreboard.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.Optional;

public class PlayerDao implements Dao<Player> {
    @Override
    public Player add(Player player) {
        Transaction transaction = null;

        try (Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
        } catch (HibernateException exception) {
            if (transaction != null) {
                transaction.rollback();
                throw new EntitiesException("Player already exists in the database");
            }
        }
        return player;
    }


    @Override
    public Optional<Player> findByName(String name) {
        Optional<Player> player;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            Query<Player> query = session.createQuery("from Player where name= :name", Player.class);
            query.setParameter("name", name);

            player = Optional.ofNullable(query.getSingleResult());
            session.getTransaction().commit();
        }
        return player;
    }
}

