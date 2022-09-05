package com.algaworks.algafood.infrastructure.repository.specification;

import com.algaworks.algafood.domain.entities.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestaurantSpecification {
    public static Specification<Restaurant> withFreeShipping() {
        return (Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) ->
                criteriaBuilder.equal(root.get("shippingRate"), BigDecimal.ZERO);
    }
}
