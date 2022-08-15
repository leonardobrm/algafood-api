package com.algaworks.algafood.domain.useCases.restaurant;

import com.algaworks.algafood.domain.entities.Kitchen;
import com.algaworks.algafood.domain.entities.Restaurant;
import com.algaworks.algafood.domain.repository.kitchen.IKitchenRepository;
import com.algaworks.algafood.domain.repository.restaurant.IRestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class RestaurantUseCaseImpl implements IRestaurantUseCase {

    private final IRestaurantRepository restaurantRepository;
    private final IKitchenRepository kitchenRepository;

    public RestaurantUseCaseImpl(IRestaurantRepository restaurantRepository, IKitchenRepository kitchenRepository) {
        this.restaurantRepository = restaurantRepository;
        this.kitchenRepository = kitchenRepository;
    }

    @Override
    public void create(String name, BigDecimal shippingRate, String kitchenName) {

        Kitchen findKitchen = this.kitchenRepository.findByName(kitchenName).orElse(new Kitchen(kitchenName));

        Restaurant findRestaurantExists = this.restaurantRepository.findByName(name).orElse(
                new Restaurant(name, shippingRate, findKitchen)
        );

        this.kitchenRepository.save(findKitchen);
        this.restaurantRepository.save(findRestaurantExists);
    }

    @Override
    public void delete(long id) {
        this.restaurantRepository.findById(id).ifPresent(this.restaurantRepository::delete);
    }

    @Override
    public void update(long id, String name, BigDecimal shippingRate) {
        var findRestaurantExists = restaurantRepository.findById(id);

        if (findRestaurantExists.isPresent()) {
            findRestaurantExists.get().updateName(name);
            findRestaurantExists.get().updateShippingRate(shippingRate);
            restaurantRepository.save(findRestaurantExists.get());
            log.info("restaurant updated successfully");
        }
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
