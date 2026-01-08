package com.example.dbtest.jparepository;

import com.example.dbtest.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepositoryJpa extends JpaRepository<News, Long> {
}