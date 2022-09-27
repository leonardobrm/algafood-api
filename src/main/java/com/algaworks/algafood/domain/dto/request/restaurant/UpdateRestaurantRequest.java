package com.algaworks.algafood.domain.dto.request.restaurant;

import java.math.BigDecimal;

public record UpdateRestaurantRequest(String name, BigDecimal shippingRate) {
}
