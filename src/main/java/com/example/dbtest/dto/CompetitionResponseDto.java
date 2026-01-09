package com.example.dbtest.dto;

import com.example.dbtest.enums.CompetitionLevel;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link com.example.dbtest.entity.Competition}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionResponseDto implements Serializable {
    private String name;
    private CompetitionLevel level;
    private Integer year;
    private ZonedDateTime startAt;
    private ZonedDateTime endAt;
    private List<TaskDto> tasks;
}