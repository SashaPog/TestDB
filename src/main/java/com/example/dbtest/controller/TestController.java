package com.example.dbtest.controller;

import com.example.dbtest.entity.User;
import com.example.dbtest.service.TestInsertingAllTables;
import com.example.dbtest.service.TestPersistService;
import com.example.dbtest.service.TestSelect;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/test")
//@APIVersion(1)
@RequiredArgsConstructor
public class TestController {
    private final TestPersistService service;
    private final TestInsertingAllTables allDataService;
    private final TestSelect select;
    private final TestSelect testSelect;

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

    @GetMapping("/get-data/{count}")
    public List<User> getAllDataRepo(@PathVariable Long count) {
        return testSelect.getTables(count);

    }

    @GetMapping("/get-data/{start}/{end}")
    public List<User> getAllUsersEntityGraph(@PathVariable Long start, @PathVariable Long end) {
        return testSelect.getUsersEntityGraph(start, end);

    }
}
