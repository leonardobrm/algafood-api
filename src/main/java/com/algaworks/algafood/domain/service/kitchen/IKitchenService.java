package com.algaworks.algafood.domain.service.kitchen;

import com.algaworks.algafood.domain.dto.request.kitchen.CreateKitchenRequest;
import com.algaworks.algafood.domain.dto.request.kitchen.UpdatedKitchenRequest;
import com.algaworks.algafood.domain.entities.Kitchen;

import java.util.List;

public interface IKitchenService {
    void create(CreateKitchenRequest request);
    void update(long id, UpdatedKitchenRequest request);
    void delete(long id);
    Kitchen findById(long id);
    List<Kitchen> findAll();
}
