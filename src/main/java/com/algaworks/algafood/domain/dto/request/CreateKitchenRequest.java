package com.algaworks.algafood.domain.dto.request;

import com.sun.istack.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateKitchenRequest {
    @NotNull
    private String name;
}
