package com.algaworks.algafood.core.dto.request.restaurant;

import java.math.BigDecimal;

public record CreateRestaurantRequest(String name, BigDecimal shippingRate, String kitchenName) {
}
