package com.example.dbtest.dto;

import com.example.dbtest.enums.CompetitionLevel;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionDto {
    private String name;
    private CompetitionLevel level;
    private Integer year;
    private ZonedDateTime startAt;
    private ZonedDateTime endAt;
    List<TaskDto> tasks;
}
