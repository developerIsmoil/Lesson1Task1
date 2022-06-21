package com.controller;

import com.entity.Department;
import com.entity.Worker;
import com.payload.ApiResponse;
import com.payload.DepartmentDto;
import com.payload.WorkerDto;
import com.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody WorkerDto workerDto) {
        return workerService.add(workerDto);
    }

    @GetMapping
    public ResponseEntity<List<Worker>> getAll() {
        List<Worker> workerList = workerService.getAll();
        return ResponseEntity.ok(workerList);
    }

    @GetMapping("/{id}")
    public Worker get(@PathVariable Long id) {
        return workerService.get(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return workerService.delete(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Long id, @Valid @RequestBody WorkerDto workerDto) {
        return workerService.edit(id, workerDto);
    }
}
