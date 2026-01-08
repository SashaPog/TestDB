package com.example.dbtest.jparepository;

import com.example.dbtest.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepositoryJpa extends JpaRepository<Submission, Long> {
}