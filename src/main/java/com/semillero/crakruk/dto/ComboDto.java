package com.semillero.crakruk.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ComboDto {

    private Long id;

    @NotBlank(message = "{error.empty_field}")
    private String name;

    @NotBlank(message = "{error.empty_field}")
    private String description;

    @NotNull(message = "{error.empty_field}")
    private Double price;

    private String image;
}
