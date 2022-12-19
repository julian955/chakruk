package com.semillero.crakruk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {

    private Long id;

    private String userName;

    private String photo;

    @NotNull(message = "{error.empty_field}")
    private String userReceiver;

    @NotNull(message = "{error.empty_field}")
    private String body;

    private Integer likes;

    private LocalDateTime created;
}
