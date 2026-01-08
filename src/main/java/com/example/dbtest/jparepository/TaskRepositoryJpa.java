package com.example.dbtest.jparepository;

import com.example.dbtest.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryJpa extends JpaRepository<Task, Long> {
}