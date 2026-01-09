package com.example.dbtest.service;

import com.example.dbtest.dto.CompetitionResponseDto;
import com.example.dbtest.dto.TaskDto;
import com.example.dbtest.entity.Competition;
import com.example.dbtest.entity.Task;
import com.example.dbtest.jparepository.CompetitionRepositoryJpa;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl {
    private final CompetitionRepositoryJpa jpa;

    @Transactional(readOnly = true)
    public CompetitionResponseDto getAllCompetitions () {
        Competition competition = jpa.getCompetitionById(1L);
        return CompetitionResponseDto.builder()
            .name(competition.getName())
            .level(competition.getLevel())
            .year(competition.getYear())
            .startAt(competition.getStartAt())
            .endAt(competition.getEndAt())
            .build();
    }


    @Transactional(readOnly = true)
    public CompetitionResponseDto getAllCompetitionsTestN1 (long count) {
        Competition competition = jpa.getCompetitionById(1L);
        List<TaskDto> tasks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
        Task task = competition.getTasks().get(i);
            tasks.add(TaskDto.builder()
                    .title(task.getTitle())
                    .fileUrl(task.getFileUrl())
                    .description(task.getDescription())
                .build());
        }

        return CompetitionResponseDto.builder()
            .name(competition.getName())
            .level(competition.getLevel())
            .year(competition.getYear())
            .startAt(competition.getStartAt())
            .endAt(competition.getEndAt())
            .tasks(tasks)
            .build();
    }

    public CompetitionResponseDto getCompetitionByIdJoinTasks (Long id, Long TasksCount){
        return null;
    }
}
