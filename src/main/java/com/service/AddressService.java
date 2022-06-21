package com.service;

import com.entity.Address;
import com.payload.AddressDto;
import com.payload.ApiResponse;
import com.repository.AddressRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRespository addressRespository;

    public ApiResponse add(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRespository.save(address);
        return new ApiResponse("Address sandiness", true);
    }

    public List<Address> getAll() {
        return addressRespository.findAll();
    }
    public Address get(Long id) {
        Optional<Address> optionalAddress = addressRespository.findById(id);
        return optionalAddress.orElse(null);
    }

    public ApiResponse delete(Long id) {
        addressRespository.deleteById(id);
        return new ApiResponse("Address o'chirildi", true);
    }

    public ApiResponse edit(Long id, AddressDto addressDto) {
        Optional<Address> optionalAddress = addressRespository.findById(id);
        if (optionalAddress.isEmpty())
            return new ApiResponse("Bunday address mavjud emas", false);
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRespository.save(address);
        return new ApiResponse("Address o'zgartrildi", true);
    }
}
