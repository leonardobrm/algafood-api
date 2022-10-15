package com.algaworks.algafood.core.dto.request.restaurant;

import java.math.BigDecimal;

public record UpdateRestaurantRequest(String name, BigDecimal shippingRate) {
}
