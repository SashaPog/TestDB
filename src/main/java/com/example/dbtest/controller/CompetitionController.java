package com.example.dbtest.controller;

import com.example.dbtest.dto.CompetitionResponseDto;
import com.example.dbtest.service.CompetitionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionServiceImpl service;

    @GetMapping
    public ResponseEntity<CompetitionResponseDto> getAllCompetitions () {
        return ResponseEntity.ok().body(service.getAllCompetitions());
    }
    @GetMapping("tasks/{count}")
    public ResponseEntity<CompetitionResponseDto> getCompetitionWithLimitedTasks(@PathVariable Long count) {
        return ResponseEntity.ok().body(service.getAllCompetitionsTestN1(count));
    }
}
