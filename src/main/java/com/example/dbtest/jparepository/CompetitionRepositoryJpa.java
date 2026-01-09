package com.example.dbtest.jparepository;

import com.example.dbtest.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepositoryJpa extends JpaRepository<Competition, Long> {

    Competition getCompetitionById(Long id);

    //test n+1
    @Query("select c from Competition c left join c.tasks where c.id = :id")
    Competition getCompetitionByIdJoinTasks (@Param("id")Long id, @Param("tasksCount") Long tasksCount);
}