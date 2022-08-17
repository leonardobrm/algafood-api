package com.algaworks.algafood.domain.dto.request.kitchen;

import com.sun.istack.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedKitchenRequest {
    @NotNull
    private String name;
}
