package com.algaworks.algafood.domain.dto.request.state;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStateRequest {
    @NotNull
    private String name;
    @NotNull
    private String uf;
}
