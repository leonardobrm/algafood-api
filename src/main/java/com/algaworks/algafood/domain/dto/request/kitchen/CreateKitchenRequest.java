package com.algaworks.algafood.domain.dto.request.kitchen;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateKitchenRequest {
    @NotNull
    private String name;
}
