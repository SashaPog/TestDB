package com.example.dbtest.repos.impl;

import com.example.dbtest.jparepository.NewsRepositoryJpa;
import com.example.dbtest.repos.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {
    private final NewsRepositoryJpa jpa;
}
