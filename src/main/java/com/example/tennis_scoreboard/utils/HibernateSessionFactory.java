package com.example.tennis_scoreboard.utils;

import com.example.tennis_scoreboard.dao.MatchDao;
import com.example.tennis_scoreboard.dao.PlayerDao;
import com.example.tennis_scoreboard.entity.Match;
import com.example.tennis_scoreboard.entity.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static final PlayerDao playerDao = new PlayerDao();
    private static final MatchDao matchDao = new MatchDao();
    private static SessionFactory sessionFactory;

    static {
        Player player1 = new Player("Сергей Жуков");
        Player player2 = new Player("Даниил Медведев");
        Player player3 = new Player("Новак Джокович");
        Player player4 = new Player("Рафаэль Надаль");
        Player player5 = new Player("Роджер Федерер");
        Player player6 = new Player("Янник Синнер");
        Player player7 = new Player("Андрей Рублев");
        Player player8 = new Player("Мариано Навоне");
        Player player9 = new Player("Томаш Махач");
        Player player10 = new Player("Карлос Алькарас");

        playerDao.add(player1);
        playerDao.add(player2);
        playerDao.add(player3);
        playerDao.add(player4);
        playerDao.add(player5);
        playerDao.add(player6);
        playerDao.add(player7);
        playerDao.add(player8);
        playerDao.add(player9);
        playerDao.add(player10);

        matchDao.add(new Match(player1, player10, player1));
        matchDao.add(new Match(player2, player9, player9));
        matchDao.add(new Match(player3, player8, player3));
        matchDao.add(new Match(player4, player7, player7));
        matchDao.add(new Match(player5, player6, player5));
        matchDao.add(new Match(player6, player5, player5));
        matchDao.add(new Match(player7, player4, player7));
        matchDao.add(new Match(player8, player3, player3));
        matchDao.add(new Match(player9, player2, player9));
        matchDao.add(new Match(player10, player1, player1));
        matchDao.add(new Match(player1, player5, player1));
        matchDao.add(new Match(player7, player3, player3));
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            var configuration = new Configuration().configure();

            configuration.addAnnotatedClass(Player.class);
            configuration.addAnnotatedClass(Match.class);

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
