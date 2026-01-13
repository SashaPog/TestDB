package com.example.dbtest.jparepository;

import com.example.dbtest.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepositoryJpa extends JpaRepository<Chat, Long> {
}