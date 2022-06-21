package com.controller;

import com.entity.Address;
import com.payload.AddressDto;
import com.payload.ApiResponse;
import com.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping
    public ApiResponse create(@Valid @RequestBody AddressDto addressDto) {
        return addressService.add(addressDto);
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAll() {
        List<Address> addressList = addressService.getAll();
        return ResponseEntity.ok(addressList);
    }

    @GetMapping("/{id}")
    public Address get(@PathVariable Long id) {
        return addressService.get(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return addressService.delete(id);
    }

    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Long id, @Valid @RequestBody AddressDto addressDto) {
        return addressService.edit(id, addressDto);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
