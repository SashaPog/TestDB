package com.example.dbtest.dto;

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
}
