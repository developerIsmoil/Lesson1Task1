package com.controller;

import com.entity.Address;
import com.entity.Company;
import com.payload.AddressDto;
import com.payload.ApiResponse;
import com.payload.CompanyDto;
import com.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody CompanyDto companyDto) {
        return companyService.add(companyDto);
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        List<Company> companyList = companyService.getAll();
        return ResponseEntity.ok(companyList);
    }

    @GetMapping("/{id}")
    public Company get(@PathVariable Long id) {
        return companyService.get(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return companyService.delete(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Long id, @Valid @RequestBody CompanyDto companyDto) {
        return companyService.edit(id, companyDto);
    }
}
