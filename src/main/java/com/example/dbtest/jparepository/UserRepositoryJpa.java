package com.example.dbtest.jparepository;

import com.example.dbtest.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long> {
    @Query("select u from User u left join fetch u.competitions c left join fetch u.tasks t where u.id >= ?1 and u.id <= ?2")
    List<User> getAllUsers (Long startId, Long endId);

    @EntityGraph(attributePaths = {
        "news",
        "competitions",
        "tasks"
    })
    @Query("""
            select u from User u
            where u.id between :start and :end
        """)
    List<User> findWithAllRelations(Long start, Long end);
}