package com.algaworks.algafood.domain.dto.request.city;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {
    @NotNull
    private String name;
    @NotNull
    private Long idState;
}
