package com.algaworks.algafood.domain.dto.request.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStateRequest {
    @Nullable
    private String name;
    @Nullable
    private String uf;
}
