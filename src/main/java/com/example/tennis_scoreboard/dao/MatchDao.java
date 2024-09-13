package com.example.tennis_scoreboard.dao;


import com.example.tennis_scoreboard.entity.Match;
import com.example.tennis_scoreboard.exception.NotFoundException;
import com.example.tennis_scoreboard.utils.HibernateSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;
import java.util.Optional;

@Slf4j
public class MatchDao implements Dao<Match> {
    @Override
    public Match add(Match match) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        }
        return match;
    }


    @Override
    public Optional<Match> findByName(String playerName) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Match> criteriaQuery = builder.createQuery(Match.class);
            Root<Match> rootEntry = criteriaQuery.from(Match.class);

            criteriaQuery.select(rootEntry)
                    .where(builder.equal(rootEntry.get("name"), playerName));

            TypedQuery<Match> query = session.createQuery(criteriaQuery);

            return Optional.of((Match) query.getResultList());
        }
    }


    public List<Match> findAllByPlayerName(String playerName, int limit, int offset) {
        List<Match> matches;
        String hql = "from Match " +
                "where player1.name like:name " +
                "or player2.name like:name " +
                "ORDER BY id DESC";
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            Query<Match> query = session.createQuery(hql, Match.class);
            query.setParameter("name", "%" + playerName + "%");
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            matches = query.getResultList();
        } catch (HibernateException exception) {
            throw new NotFoundException("matches not found");
        }
        return matches;
    }


    public List<Match> findAll(int limit, int offset) {
        String hql = "FROM Match";
        List<Match> matches;

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            Query<Match> query = session.createQuery(hql, Match.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);

            matches = query.getResultList();
        } catch (HibernateException exception) {
            throw new NotFoundException("matches not found");
        }
        return matches;
    }


    public int countMatches(String filterName) {
        if (filterName == null || filterName.isEmpty()) {
            String hql = "SELECT COUNT(*) FROM Match m";
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

                Query<Long> query = session.createQuery(hql, Long.class);

                return query.uniqueResult().intValue();
            } catch (HibernateException exception) {
                throw new NotFoundException("matches with player name: " + filterName + " not found");
            }
        } else {
            String hql = "SELECT COUNT(*) FROM Match m WHERE m.player1.name like :name OR m.player2.name like :name";
            try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

                Query<Long> query = session.createQuery(hql, Long.class);
                query.setParameter("name", "%" + filterName + "%");

                return query.uniqueResult().intValue();
            } catch (HibernateException exception) {
                throw new NotFoundException("matches with player name: " + filterName + " not found");
            }
        }
    }


    public List<Match> findAllWithPagination(String filterName, int pageSize, int pageNumber) {
        int offset = (pageNumber - 1) * pageSize;

        List<Match> allMatches;

        if (filterName == null || filterName.trim().isEmpty()) {
            allMatches = findAll(pageSize, offset);
        } else {
            allMatches = findAllByPlayerName(filterName, pageSize, offset);
        }
        return allMatches;
    }
}
