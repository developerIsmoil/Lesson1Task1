package com.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    @NotNull(message = "compName not null")
    private String compName;
    @NotNull(message = "directorName not null")
    private String directorName;
    @NotNull(message = "addressId not null")
    private Long addressId;
}
