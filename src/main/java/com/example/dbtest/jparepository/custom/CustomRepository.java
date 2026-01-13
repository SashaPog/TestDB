package com.example.dbtest.jparepository.custom;

import com.example.dbtest.entity.Competition;
import com.example.dbtest.entity.News;
import com.example.dbtest.entity.Task;
import com.example.dbtest.entity.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CustomRepository {
    private final EntityManager em;

    @Transactional(readOnly = true)
    public List<User> findAllInRangeFetchCompetitionAndTasks (Long startId, Long endId) {
        var user = em.createQuery(
            "select u from User u left join fetch u.competitions where u.id >= ?1 and u.id <= ?2",
            User.class
        )
            .setParameter(1, startId)
            .setParameter(2, endId)
            .getResultList();

        user = em.createQuery(
                "select u from User u left join fetch u.tasks where u in ?1",
            User.class
        )
            .setParameter(1, user)
            .getResultList();

        return user;
    }


    @Transactional(readOnly = true)
    public List<User> findAllTables (Long startId, Long endId) {
        var user = em.createQuery(
                "select distinct u from User u left join fetch u.news where u.id >= ?1 and u.id <= ?2",
                User.class
            )
            .setParameter(1, startId)
            .setParameter(2, endId)
            .getResultList();

        user = em.createQuery(
                "select distinct u from User u left join fetch u.competitions where u in ?1",
                User.class
            )
            .setParameter(1, user)
            .getResultList();

        user = em.createQuery(
                "select distinct u from User u left join fetch u.tasks where u in ?1",
                User.class
            )
            .setParameter(1, user)
            .getResultList();

        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAllTablesNew(Long startId, Long endId) {

        List<User> users = em.createQuery("""
        select u from User u
        where u.id between :start and :end
    """, User.class)
            .setParameter("start", startId)
            .setParameter("end", endId)
            .getResultList();

        if (users.isEmpty()) {
            return users;
        }

        List<Long> ids = users.stream()
            .map(User::getId)
            .toList();

        em.createQuery("""
        select n from News n
        where n.createdBy.id in :ids
    """, News.class)
            .setParameter("ids", ids)
            .getResultList();

//        em.createQuery("""
//        select c from Competition c
//        where c.users.id in :ids
//    """, Competition.class)
//            .setParameter("ids", ids)
//            .getResultList();

        em.createQuery("""
        select t from Task t
        where t.author.id in :ids
    """, Task.class)
            .setParameter("ids", ids)
            .getResultList();

        return users;
    }
}
