package com.algaworks.algafood.domain.dto.request.city;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {
    private String name;
    private Long idState;
}
