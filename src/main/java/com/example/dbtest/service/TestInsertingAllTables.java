package com.example.dbtest.service;

import com.example.dbtest.entity.Competition;
import com.example.dbtest.entity.Evaluation;
import com.example.dbtest.entity.News;
import com.example.dbtest.entity.Submission;
import com.example.dbtest.entity.Task;
import com.example.dbtest.entity.User;
import com.example.dbtest.enums.CompetitionLevel;
import com.example.dbtest.enums.Role;
import com.example.dbtest.enums.UserStatus;
import com.example.dbtest.jparepository.UserRepositoryJpa;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestInsertingAllTables {
    private static final int BATCH_SIZE = 50;
    private final EntityManager em;
    private final UserRepositoryJpa jpa;

    //public void insertAllData(int userCount,
    //                              int competitionCount,
    //                              int tasksPerCompetition,
    //                              int submissionsPerTask,
    //                              int evaluationsPerSubmission

    @Transactional
    public void insertAllData(int count) {
        int batchSize = 50;

        // ========================
        // 1️⃣ Users
        // ========================
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            User user = User.builder()
                .uuid(UUID.randomUUID().toString())
                .email("email" + UUID.randomUUID() + "@gmail.com")
                .password("pass" + i)
                .firstName("first" + i)
                .lastName("last" + i)
                .role(Role.USER)
                .userStatus(UserStatus.ACTIVATED)
                .createdAt(Instant.now())
                .build();
            em.persist(user);
            users.add(user);

            if (i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
        em.flush();
        em.clear();

        // ========================
        // 2️⃣ Competitions
        // ========================
        List<Competition> competitions = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Competition competition = Competition.builder()
                .name("Competition " + i)
                .level(CompetitionLevel.CITY)
                .year(2026)
                .startAt(ZonedDateTime.now())
                .endAt(ZonedDateTime.now().plusDays(30))
                .build();

            // Додаємо користувачів до конкурсу
            for (int j = 0; j < Math.min(5, users.size()); j++) {
                User u = users.get((i + j) % users.size());
                competition.addUser(u); // ManyToMany
            }

            em.persist(competition);
            competitions.add(competition);

            if (i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
        em.flush();
        em.clear();

        // ========================
        // 3️⃣ News
        // ========================
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            News news = News.builder()
                .title("News " + i)
                .text("Some text")
                .createdAt(Instant.now())
                .createdBy(u)
                .build();
            em.persist(news);

            if (i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
        em.flush();
        em.clear();

        // ========================
        // 4️⃣ Tasks
        // ========================
        List<Task> tasks = new ArrayList<>();
        for (Competition competition : competitions) {
            for (int t = 0; t < 20000; t++) {
                User author = users.get((t + competition.getId().intValue()) % users.size());
                Task task = Task.builder()
                    .title("Task " + t + " for " + competition.getName())
                    .description("Description " + t)
                    .fileUrl("file" + t + ".txt")
                    .author(author)
                    .competition(competition)
                    .build();
                em.persist(task);
                tasks.add(task);

                if (tasks.size() % batchSize == 0) {
                    em.flush();
                    em.clear();
                }
            }
        }
        em.flush();
        em.clear();

        // ========================
        // 5️⃣ Submissions
        // ========================
        List<Submission> submissions = new ArrayList<>();
        for (Task task : tasks) {
            for (int s = 0; s < 2; s++) {
                User user = users.get((s + task.getId().intValue()) % users.size());
                Submission submission = Submission.builder()
                    .fileUrl("submission" + s + ".txt")
                    .comment("Comment " + s)
                    .submittedAt(Instant.now())
                    .task(task)
                    .user(user)
                    .build();
                em.persist(submission);
                submissions.add(submission);

                if (submissions.size() % batchSize == 0) {
                    em.flush();
                    em.clear();
                }
            }
        }
        em.flush();
        em.clear();

        // ========================
        // 6️⃣ Evaluations
        // ========================
        for (Submission submission : submissions) {
            for (int e = 0; e < 2; e++) {
                User jury = users.get((e + submission.getId().intValue()) % users.size());
                Evaluation evaluation = Evaluation.builder()
                    .score((int) (Math.random() * 100))
                    .comment("Eval comment " + e)
                    .submission(submission)
                    .jury(jury)
                    .build();
                em.persist(evaluation);

                if (e % batchSize == 0) {
                    em.flush();
                    em.clear();
                }
            }
        }
        em.flush();
        em.clear();
    }
}
