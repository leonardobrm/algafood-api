package com.algaworks.algafood.domain.dto.request;

import com.sun.istack.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRestaurantRequest {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal shippingRate;
    @NotNull
    private String kitchenName;
}
