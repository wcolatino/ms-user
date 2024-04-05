package com.microservice.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Record - pois é imutável, possui construtor, getter, setter e elimina a necessidade de imports;
 * */
public record UserRecordDto(@NotBlank String name,
                            @NotBlank @Email String email) {
}
