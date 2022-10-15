package com.algaworks.algafood.core.usecases.restaurant;

import com.algaworks.algafood.core.dto.request.restaurant.CreateRestaurantRequest;
import com.algaworks.algafood.core.dto.request.restaurant.UpdateRestaurantRequest;
import com.algaworks.algafood.core.model.Kitchen;
import com.algaworks.algafood.core.model.Restaurant;
import com.algaworks.algafood.infrastructure.exception.errors.ApiException;
import com.algaworks.algafood.infrastructure.repository.kitchen.IKitchenRepository;
import com.algaworks.algafood.infrastructure.repository.restaurant.IRestaurantRepository;
import com.algaworks.algafood.infrastructure.specification.RestaurantSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Slf4j
@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements IRestaurantService {

    private final IRestaurantRepository restaurantRepository;
    private final IKitchenRepository kitchenRepository;

    @Override
    public void create(final CreateRestaurantRequest request) {
        final var findKitchen = this.kitchenRepository.findByName(request.kitchenName());
        final var kitchen = findKitchen.isEmpty() ? new Kitchen(request.kitchenName()) : findKitchen.get();
        if (findKitchen.isEmpty()) this.kitchenRepository.save(kitchen);
        final var findRestaurantExists = this.restaurantRepository.findByName(request.name()).stream().findFirst();
        if (findRestaurantExists.isPresent())
            throw new ApiException("Restaurant already exists", HttpStatus.BAD_REQUEST);
       final var newRestaurant = new Restaurant(request.name(), request.shippingRate(), kitchen);
        this.restaurantRepository.save(newRestaurant);
        log.info("Restaurant created successfully");
    }

    @Override
    public void delete(long id) {
        this.restaurantRepository.findById(id).ifPresent(this.restaurantRepository::delete);
    }

    @Override
    public void update(long id, UpdateRestaurantRequest request) {
        var findRestaurantExists = restaurantRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("Restaurant not found", HttpStatus.NOT_FOUND);
        });
        BeanUtils.copyProperties(request, findRestaurantExists, "id", "kitchen", "created_at", "formOfPayments", "address", "products");
        restaurantRepository.save(findRestaurantExists);
        log.info("restaurant updated successfully");
    }

    @Override
    public List<Restaurant> findAll() {
        return this.restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(long id) {
        Supplier supplier = () -> new ApiException("Restaurant not found", HttpStatus.NOT_FOUND);
        return this.restaurantRepository.findById(id).orElseThrow(supplier);
    }

    @Override
    public List<Restaurant> findAllWithFreeShipping() {
        return this.restaurantRepository.findAll(RestaurantSpecification.withFreeShipping());
    }
}
