package com.example.dbtest.repos.impl;

import com.example.dbtest.jparepository.TaskRepositoryJpa;
import com.example.dbtest.repos.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {
    private final TaskRepositoryJpa jpa;
}
