package com.example.dbtest.controller;

import com.example.dbtest.service.TestInsertingAllTables;
import com.example.dbtest.service.TestPersistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/test")
@RequiredArgsConstructor
public class TestController {
    private final TestPersistService service;
    private final TestInsertingAllTables allDataService;

    @PostMapping("/users/{count}")
    public String insert (@PathVariable int count) {
        long start = System.currentTimeMillis();
        service.insertUserDataJpa(count);
        return "Inserted in" + (System.currentTimeMillis() - start) + " ms";
    }

    @PostMapping("/data/{count}")
    public String insertAllData (@PathVariable int count) {
        long start = System.currentTimeMillis();
        allDataService.insertAllData(count);
        return "Inserted in" + (System.currentTimeMillis() - start) + " ms";
    }
}
