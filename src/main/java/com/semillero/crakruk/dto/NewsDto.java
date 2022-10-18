package com.semillero.crakruk.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class NewsDto {
    private Long id;

    @NotNull(message = "{error.empty_field}")
    private String name;

    @NotNull(message = "{error.empty_field}")
    private String content;

    private String image;


}

