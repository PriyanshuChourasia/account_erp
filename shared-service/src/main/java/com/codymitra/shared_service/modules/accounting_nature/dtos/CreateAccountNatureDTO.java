package com.codymitra.shared_service.modules.accounting_nature.dtos;


public record CreateAccountNatureDTO(
        String name,
        Integer code,
        String description
) {}
