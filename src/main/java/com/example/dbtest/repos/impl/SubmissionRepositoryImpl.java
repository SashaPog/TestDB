package com.example.dbtest.repos.impl;

import com.example.dbtest.jparepository.SubmissionRepositoryJpa;
import com.example.dbtest.repos.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubmissionRepositoryImpl implements SubmissionRepository {
    private final SubmissionRepositoryJpa jpa;
}
