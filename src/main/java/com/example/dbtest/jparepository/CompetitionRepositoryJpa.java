package com.example.dbtest.jparepository;

import com.example.dbtest.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepositoryJpa extends JpaRepository<Competition, Long> {
}