package com.algaworks.algafood.domain.dto.request.restaurant;

import com.sun.istack.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRestaurantRequest {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal shippingRate;
}
