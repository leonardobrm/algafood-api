package com.algaworks.algafood.domain.dto.request.restaurant;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
