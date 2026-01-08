package com.example.dbtest.repos.impl;

import com.example.dbtest.jparepository.EvaluationRepositoryJpa;
import com.example.dbtest.repos.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationRepositoryImpl implements EvaluationRepository {
    private final EvaluationRepositoryJpa jpa;
}
