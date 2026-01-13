package com.example.dbtest.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private String title;
    private String description;
    private String fileUrl;
    private Long authorId;
    private Long competitionId;
    private List<SubmissionDto> submissions;
}
