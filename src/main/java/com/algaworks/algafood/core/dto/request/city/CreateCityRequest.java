package com.algaworks.algafood.core.dto.request.city;

import org.springframework.lang.NonNull;

public record CreateCityRequest(
        @NonNull
        String name,
        @NonNull
        Long idState) {
};


