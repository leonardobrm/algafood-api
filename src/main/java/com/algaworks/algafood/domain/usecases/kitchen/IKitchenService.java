package com.algaworks.algafood.domain.usecases.kitchen;

import com.algaworks.algafood.domain.dto.request.kitchen.CreateKitchenRequest;
import com.algaworks.algafood.domain.dto.request.kitchen.UpdatedKitchenRequest;
import com.algaworks.algafood.domain.model.Kitchen;

import java.util.List;

public interface IKitchenService {
    void create(final CreateKitchenRequest request);
    void update(final long id, UpdatedKitchenRequest request);
    void delete(final long id);
    Kitchen findById(final long id);
    List<Kitchen> findAll();
}