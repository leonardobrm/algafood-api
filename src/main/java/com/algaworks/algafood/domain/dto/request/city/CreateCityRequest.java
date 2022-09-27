package com.algaworks.algafood.domain.dto.request.city;

import org.springframework.lang.NonNull;

public record CreateCityRequest(
        @NonNull
        String name,
        @NonNull
        Long idState) {
};


