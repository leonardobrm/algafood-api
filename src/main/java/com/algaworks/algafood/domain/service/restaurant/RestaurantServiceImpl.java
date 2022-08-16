package com.algaworks.algafood.domain.service.restaurant;

import com.algaworks.algafood.domain.dto.request.CreateRestaurantRequest;
import com.algaworks.algafood.domain.dto.request.UpdateRestaurantRequest;
import com.algaworks.algafood.domain.entities.Kitchen;
import com.algaworks.algafood.domain.entities.Restaurant;
import com.algaworks.algafood.domain.exception.errors.ApiException;
import com.algaworks.algafood.domain.repository.kitchen.IKitchenRepository;
import com.algaworks.algafood.domain.repository.restaurant.IRestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RestaurantServiceImpl implements IRestaurantService {

    private final IRestaurantRepository restaurantRepository;
    private final IKitchenRepository kitchenRepository;

    public RestaurantServiceImpl(IRestaurantRepository restaurantRepository, IKitchenRepository kitchenRepository) {
        this.restaurantRepository = restaurantRepository;
        this.kitchenRepository = kitchenRepository;
    }

    @Override
    public void create(CreateRestaurantRequest request) {
        Optional<Kitchen> findKitchen = this.kitchenRepository.findByName(request.getKitchenName());
        Kitchen kitchen = findKitchen.isEmpty() ? new Kitchen(request.getKitchenName()) : findKitchen.get();

        if (findKitchen.isEmpty()) this.kitchenRepository.save(kitchen);

        Optional<Restaurant> findRestaurantExists = this.restaurantRepository.findByName(request.getName()).stream().findFirst();

        if (findRestaurantExists.isPresent())
            throw new ApiException("Restaurant already exists", HttpStatus.BAD_REQUEST);

        Restaurant newRestaurant = new Restaurant(request.getName(), request.getShippingRate(), kitchen);
        this.restaurantRepository.save(newRestaurant);
        log.info("Restaurant created successfully");
    }

    @Override
    public void delete(long id) {
        this.restaurantRepository.findById(id).ifPresent(this.restaurantRepository::delete);
    }

    @Override
    public void update(long id, UpdateRestaurantRequest request) {
        Restaurant findRestaurantExists = restaurantRepository.findById(id).orElseThrow(() -> new Error("Restaurant not found"));
        BeanUtils.copyProperties(request, findRestaurantExists, "id", "kitchen", "created_at");

        restaurantRepository.save(findRestaurantExists);
        log.info("restaurant updated successfully");
    }

    @Override
    public List<Restaurant> getAll() {
        return this.restaurantRepository.findAll(Sort.by("name"));
    }

    @Override
    public Restaurant get(long id) {
        return this.restaurantRepository.findById(id).orElse(null);
    }
}