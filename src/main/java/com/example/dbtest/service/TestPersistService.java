package com.example.dbtest.service;

import com.example.dbtest.entity.User;
import com.example.dbtest.enums.Role;
import com.example.dbtest.enums.UserStatus;
import com.example.dbtest.jparepository.UserRepositoryJpa;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestPersistService {
    private final EntityManager em;
    private static final  int BATCH_SIZE = 50;
    private final UserRepositoryJpa jpa;

    @Transactional
    public void insertUserData (int count) {
        for(int i = 0; i < count; i++) {
            User user = addUser(i);

            em.persist(user);
//            if (i % BATCH_SIZE == 0) {
//                em.flush();
//                em.clear();
//            }
        }
    }

    private User addUser(int i) {
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setEmail(String.format("email%s@gmail.com", UUID.randomUUID()));
        user.setPassword(String.format("password%s", i));
        user.setFirstName("firstName" + i);
        user.setLastName("LastName" + i);
        user.setRole(Role.USER);
        user.setUserStatus(UserStatus.ACTIVATED);
        user.setCreatedAt(Instant.now());
        return user;
    }

    public void insertUserDataJpa (int count) {
        for(int i = 0; i < count; i++) {
            User user = addUser(i);
            jpa.save(user);
        }
    }
}
