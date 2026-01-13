package com.example.dbtest.service;

import com.example.dbtest.dto.SubmissionDto;
import com.example.dbtest.dto.TaskResponseDto;
import com.example.dbtest.entity.Submission;
import com.example.dbtest.entity.Task;
import com.example.dbtest.jparepository.TaskRepositoryJpa;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl {
    private final TaskRepositoryJpa jpa;

    @Transactional(readOnly = true)
    public List<TaskResponseDto> getAllTasks(Long start, Long end) {
        List<Task> tasks = jpa.findAllByIdGreaterThanEqualAndIdLessThan(start, end);

        List<TaskResponseDto> tasksDto = BuildListOfTaskResponceDto(tasks);

        return tasksDto;
    }

    private static List<TaskResponseDto> BuildListOfTaskResponceDto(List<Task> tasks) {
        List<TaskResponseDto> tasksDto = new ArrayList<>();

        for (Task task : tasks) {
            List<Submission> submissions = task.getSubmissions();
//            log.warn("Get N+1 Submission");
            List<SubmissionDto> subDtoList = new ArrayList<>();

            for (Submission sub : submissions) {
                subDtoList.add(SubmissionDto.builder()
                    .fileUrl(sub.getFileUrl())
                    .comment(sub.getComment())
                    .submittedAt(sub.getSubmittedAt())
                    .build());
            }

            tasksDto.add(
                TaskResponseDto.builder()
                    .title(task.getTitle())
                    .fileUrl(task.getFileUrl())
                    .description(task.getDescription())
                    .authorId(1L)
                    .submissions(subDtoList)
                    .build()
            );
        }
        return tasksDto;
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> getAllTasksJoinFetch(Long start, Long end) {
        long t0 = System.currentTimeMillis();
        List<Task> tasks = jpa.findAllByIdJoinFetch(start, end);

        List<TaskResponseDto> tasksDto = BuildListOfTaskResponceDto(tasks);

        long t1 = System.currentTimeMillis();
        log.info("get with join fetch: {}ms", t1 - t0);
        return tasksDto;
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> getTaskDtoJpa (Long start, Long end) {
        long t0 = System.currentTimeMillis();
        var dto = jpa.findTasksOnly(start, end);
        long t1 = System.currentTimeMillis();
        log.info("DB time REPO -> DTO: {}ms", t1 - t0);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> getTaskDtoJpa2 (Long start, Long end) {
        long t0 = System.currentTimeMillis();
        List<Task> tasks = jpa.findAllByIdQuery(start, end);

        List<TaskResponseDto> tasksDto = new ArrayList<>();

        for (Task task : tasks) {
            tasksDto.add(
                TaskResponseDto.builder()
                    .title(task.getTitle())
                    .fileUrl(task.getFileUrl())
                    .description(task.getDescription())
                    .authorId(task.getAuthor().getId())
                    .competitionId(task.getAuthor().getId())
                    .build()
            );
        }

        long t1 = System.currentTimeMillis();
        log.info("DB time REPO -> ENTITY -> DTO: {}ms", t1 - t0);
        return tasksDto;
    }
}
