package com.example.dbtest.repos.impl;

import com.example.dbtest.jparepository.UserRepositoryJpa;
import com.example.dbtest.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJpa jpa;
}
