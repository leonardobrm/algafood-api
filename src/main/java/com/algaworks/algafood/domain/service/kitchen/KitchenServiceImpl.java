package com.algaworks.algafood.domain.service.kitchen;

import com.algaworks.algafood.domain.dto.request.kitchen.CreateKitchenRequest;
import com.algaworks.algafood.domain.dto.request.kitchen.UpdatedKitchenRequest;
import com.algaworks.algafood.domain.entities.Kitchen;
import com.algaworks.algafood.domain.exception.errors.ApiException;
import com.algaworks.algafood.domain.repository.kitchen.IKitchenRepository;
import com.algaworks.algafood.domain.repository.restaurant.IRestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class KitchenServiceImpl implements IKitchenService {

    //TODO -> Refact on logs.

    private final IKitchenRepository kitchenRepository;
    private final IRestaurantRepository restaurantRepository;

    public KitchenServiceImpl(IKitchenRepository kitchenRepository, IRestaurantRepository restaurantRepository) {
        this.kitchenRepository = kitchenRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void create(CreateKitchenRequest request) {
        Optional<Kitchen> kitchenAlreadyExists = this.kitchenRepository.findByName(request.getName());
        if (kitchenAlreadyExists.isPresent()) throw new ApiException("Kitchen already exists", HttpStatus.BAD_REQUEST);

        this.kitchenRepository.save(new Kitchen(request.getName()));
        log.info("Kitchen created successufully");
    }

    @Override
    public void update(long id, UpdatedKitchenRequest request) {
        Kitchen findKitchenExists = kitchenRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("Kitchen not exists", HttpStatus.NOT_FOUND);
        });

        BeanUtils.copyProperties(request, findKitchenExists, "id");
        kitchenRepository.save(findKitchenExists);
        log.info("Kitchen updated successfully");
    }

    @Override
    public void delete(long id) {
        Kitchen findKitchenExists = kitchenRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("Kitchen not found", HttpStatus.NOT_FOUND);
        });

        //validated if there is a restaurant using this kitchen
        this.restaurantRepository.findByKitchenId(findKitchenExists.getId()).ifPresent(restaurant -> {
            if (restaurant.size() > 0)
                throw new ApiException("There is a restaurant using the kitchen", HttpStatus.CONFLICT);
        });
        kitchenRepository.delete(findKitchenExists);
        log.info("Kitchen deleted successfully");
    }

    @Override
    public Kitchen findById(long id) {
        return this.kitchenRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("Kitchen not found", HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public List<Kitchen> findAll() {
        return this.kitchenRepository.findAll();
    }
}
