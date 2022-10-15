package com.algaworks.algafood.core.dto.request.city;

import org.springframework.lang.NonNull;

public record UpdateCityRequest(
        @NonNull
        String name,
        @NonNull
        Long idState
) {
}
