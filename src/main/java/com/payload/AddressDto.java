package com.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotNull(message = "street null bo'lishi mumkin emas")
    private String street;
    @NotNull(message = "homeNumber null bo'lishi mumkin emas")
    private Long homeNumber;
}
