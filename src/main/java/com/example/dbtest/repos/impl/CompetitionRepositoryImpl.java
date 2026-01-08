package com.example.dbtest.repos.impl;

import com.example.dbtest.jparepository.CompetitionRepositoryJpa;
import com.example.dbtest.repos.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompetitionRepositoryImpl implements CompetitionRepository {
    private final CompetitionRepositoryJpa jpa;
}
