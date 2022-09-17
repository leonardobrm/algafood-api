package com.algaworks.algafood.domain.service.kitchen;

import com.algaworks.algafood.domain.dto.request.kitchen.CreateKitchenRequest;
import com.algaworks.algafood.domain.dto.request.kitchen.UpdatedKitchenRequest;
import com.algaworks.algafood.domain.entities.Kitchen;
import com.algaworks.algafood.infrastructure.exception.errors.ApiException;
import com.algaworks.algafood.infrastructure.repository.kitchen.IKitchenRepository;
import com.algaworks.algafood.infrastructure.repository.restaurant.IRestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class KitchenServiceImpl implements IKitchenService {

    //TODO -> Refact on logs.

    private final IKitchenRepository kitchenRepository;
    private final IRestaurantRepository restaurantRepository;

    @Override
    public void create(final CreateKitchenRequest request) {
        var kitchenAlreadyExists = this.kitchenRepository.findByName(request.getName());
        if (kitchenAlreadyExists.isPresent()) throw new ApiException("Kitchen already exists", HttpStatus.BAD_REQUEST);

        this.kitchenRepository.save(new Kitchen(request.getName()));
        log.info("Kitchen created successufully");
    }

    @Override
    public void update(final long id, final UpdatedKitchenRequest request) {
        var findKitchenExists = kitchenRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("Kitchen not exists", HttpStatus.NOT_FOUND);
        });

        BeanUtils.copyProperties(request, findKitchenExists, "id");
        kitchenRepository.save(findKitchenExists);
        log.info("Kitchen updated successfully");
    }

    @Override
    public void delete(final long id) {
        var findKitchenExists = kitchenRepository.findById(id).orElseThrow(() -> {
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
    public Kitchen findById(final long id) {
        return this.kitchenRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("Kitchen not found", HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public List<Kitchen> findAll() {
        return this.kitchenRepository.findAll();
    }
}
