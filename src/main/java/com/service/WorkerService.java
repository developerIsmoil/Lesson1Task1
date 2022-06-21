package com.service;

import com.entity.Address;
import com.entity.Department;
import com.entity.Worker;
import com.payload.ApiResponse;
import com.payload.WorkerDto;
import com.repository.AddressRespository;
import com.repository.DepartmentRespository;
import com.repository.WorkerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRespository workerRespository;
    @Autowired
    AddressRespository addressRespository;
    @Autowired
    DepartmentRespository departmentRespository;

    public ApiResponse add(WorkerDto workerDto) {
        Optional<Address> optionalAddress = addressRespository.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRespository.findById(workerDto.getDepartmentId());
        if (optionalAddress.isEmpty())
            return new ApiResponse("Address mavjud emas", false);
        if (optionalDepartment.isEmpty())
            return new ApiResponse("Department mavjud emas", false);
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        return new ApiResponse("Saqlandi", true);
    }

    public List<Worker> getAll() {
        return workerRespository.findAll();
    }

    public Worker get(Long id) {
        Optional<Worker> optionalWorker = workerRespository.findById(id);
        return optionalWorker.orElse(null);
    }

    public ApiResponse delete(Long id) {
        workerRespository.deleteById(id);
        return new ApiResponse("Worker o'chirildi", true);
    }

    public ApiResponse edit(Long id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRespository.findById(id);
        if (optionalWorker.isEmpty())
            return new ApiResponse("Bunday worker mavjud emas", false);
        Optional<Address> optionalAddress = addressRespository.findById(workerDto.getAddressId());
        if (optionalAddress.isEmpty())
            return new ApiResponse("Bunday addess mavjud emas", false);
        Optional<Department> optionalDepartment = departmentRespository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty())
            return new ApiResponse("Bunday department mavjud emas", false);

        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(optionalAddress.get());
        worker.setDepartment(optionalDepartment.get());
        workerRespository.save(worker);
        return new ApiResponse("Succesfully editing", true);
    }
}
