package com.service;

import com.entity.Company;
import com.entity.Department;
import com.payload.ApiResponse;
import com.payload.DepartmentDto;
import com.repository.CompanyRespository;
import com.repository.DepartmentRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRespository departmentRespository;
    @Autowired
    CompanyRespository companyRespository;

    public ApiResponse add(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRespository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isPresent()) {
            Department department = new Department();
            department.setName(departmentDto.getName());
            department.setCompany(optionalCompany.get());
            departmentRespository.save(department);
            return new ApiResponse("Department saqlandi", true);
        }
        return new ApiResponse("Company topilmadi", false);
    }

    public List<Department> getAll() {
        return departmentRespository.findAll();
    }

    public Department get(Long id) {
        Optional<Department> optionalDepartment = departmentRespository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public ApiResponse delete(Long id) {
        departmentRespository.deleteById(id);
        return new ApiResponse("Department o'chirildi", true);
    }

    public ApiResponse edit(Long id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRespository.findById(id);
        if (optionalDepartment.isEmpty())
            return new ApiResponse("Bunday department mavjud emas", false);
        Optional<Company> optionalCompany = companyRespository.findById(id);
        if (optionalCompany.isPresent()) {
            Department department = new Department();
            department.setName(departmentDto.getName());
            department.setCompany(optionalCompany.get());
            departmentRespository.save(department);
            return new ApiResponse("Succesfully editing", true);
        }
        return new ApiResponse("Bunday Company mavjud emas", false);
    }
}
