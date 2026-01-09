package com.example.dbtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationDto {
    private int score;
    private String comment;
    private Long submissionId;
    private Long juryId;
}
