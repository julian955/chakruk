package com.semillero.crakruk.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CommentDto {

    private Long id;

    @NotNull(message = "{error.empty_field}")
    private Long userId;

    @NotNull(message = "{error.empty_field}")
    private String body;
}
