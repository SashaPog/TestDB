package com.example.dbtest.controller;

import com.example.dbtest.dto.TaskResponseDto;
import com.example.dbtest.service.TaskServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskServiceImpl service;


    @GetMapping("/{start}/{end}")
    public ResponseEntity<List<TaskResponseDto>> getAll (@PathVariable Long start, @PathVariable Long end) {
        return ResponseEntity.ok().body(service.getAllTasks(start, end));
    }

    @GetMapping("/fetch/{start}/{end}")
    public ResponseEntity<List<TaskResponseDto>> getAllJoinFetch (@PathVariable Long start, @PathVariable Long end) {
        return ResponseEntity.ok().body(service.getAllTasksJoinFetch(start, end));
    }

    @GetMapping("/get-dto/{start}/{end}")
    public ResponseEntity<List<TaskResponseDto>> getAllDtoJpa (@PathVariable Long start, @PathVariable Long end) {
        return ResponseEntity.ok().body(service.getTaskDtoJpa(start, end));
    }

    @GetMapping("/loop-dto/{start}/{end}")
    public ResponseEntity<List<TaskResponseDto>> getAllLoopDto (@PathVariable Long start, @PathVariable Long end) {
        return ResponseEntity.ok().body(service.getTaskDtoJpa2(start, end));
    }


}
