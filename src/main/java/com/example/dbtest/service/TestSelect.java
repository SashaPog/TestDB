package com.example.dbtest.service;

import com.example.dbtest.entity.User;
import com.example.dbtest.jparepository.CompetitionRepositoryJpa;
import com.example.dbtest.jparepository.NewsRepositoryJpa;
import com.example.dbtest.jparepository.TaskRepositoryJpa;
import com.example.dbtest.jparepository.UserRepositoryJpa;
import com.example.dbtest.jparepository.custom.CustomRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestSelect {
    private final UserRepositoryJpa userJpa;
    private final NewsRepositoryJpa newsJpa;
    private final CompetitionRepositoryJpa compJpa;
    private final TaskRepositoryJpa taskjpa;

    private final CustomRepository repo;

    public List<User> getTables (Long count) {
        var start = System.currentTimeMillis();
        List<User> users = repo.findAllTablesNew(1L, count);
        var user = users.getFirst();
//        System.out.println(user.toString());
//        System.out.println(user.getCompetitions().toString());
//        System.out.println(user.getNews().toString());
        long time = System.currentTimeMillis() - start;
        log.warn("time ms: ${}", time);
//        return "time ms: " + time;
        return users;
    }

    public List<User> getUsersEntityGraph (Long startId, Long endId) {
        var start = System.currentTimeMillis();
        List<User> users = userJpa.findWithAllRelations(startId, endId);
        long time = System.currentTimeMillis() - start;
        log.warn("time ms: ${}", time);
        return users;
    }

}
