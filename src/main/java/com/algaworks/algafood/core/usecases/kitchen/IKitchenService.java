package com.algaworks.algafood.core.usecases.kitchen;

import com.algaworks.algafood.core.dto.request.kitchen.CreateKitchenRequest;
import com.algaworks.algafood.core.dto.request.kitchen.UpdatedKitchenRequest;
import com.algaworks.algafood.core.model.Kitchen;

import java.util.List;

public interface IKitchenService {
    void create(final CreateKitchenRequest request);
    void update(final long id, UpdatedKitchenRequest request);
    void delete(final long id);
    Kitchen findById(final long id);
    List<Kitchen> findAll();
}
