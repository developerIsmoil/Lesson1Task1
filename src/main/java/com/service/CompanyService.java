package com.service;

import com.entity.Address;
import com.entity.Company;
import com.payload.ApiResponse;
import com.payload.CompanyDto;
import com.repository.AddressRespository;
import com.repository.CompanyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRespository companyRespository;
    @Autowired
    AddressRespository addressRespository;

    public ApiResponse add(CompanyDto companyDto) {
        Optional<Address> address = addressRespository.findById(companyDto.getAddressId());
        if (address.isPresent()) {
            Company company = new Company();
            company.setCompName(companyDto.getCompName());
            company.setDirectorName(companyDto.getDirectorName());
            company.setAddress(address.get());
            companyRespository.save(company);
            return new ApiResponse("Company saqlandi", true);
        }
        return null;
    }

    public List<Company> getAll() {
        return companyRespository.findAll();
    }

    public Company get(Long id) {
        Optional<Company> optionalCompany = companyRespository.findById(id);
        return optionalCompany.orElse(null);
    }

    public ApiResponse delete(Long id) {
        companyRespository.deleteById(id);
        return new ApiResponse("Company o'chirildi", true);
    }

    public ApiResponse edit(Long id, CompanyDto companyDto) {
        Optional<Address> optionalAddress = addressRespository.findById(companyDto.getAddressId());
        if (optionalAddress.isEmpty()) {
            return new ApiResponse("Bunday Address mavjud emas", false);
        }
        Optional<Company> optionalCompany = companyRespository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = new Company();
            company.setCompName(companyDto.getCompName());
            company.setDirectorName(companyDto.getDirectorName());
            company.setAddress(optionalAddress.get());
            companyRespository.save(company);
            return new ApiResponse("Succesfully editing", true);
        }
        return new ApiResponse("Bunday Company mavjud emas", false);
    }
}
