package com.semillero.crakruk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboDto {

    private Long id;

    @NotNull(message = "{error.empty_field}")
    private String name;

    @NotNull(message = "{error.empty_field}")
    private List<String> description;

    @NotNull(message = "{error.empty_field}")
    private Double price;

    private String image;
}
