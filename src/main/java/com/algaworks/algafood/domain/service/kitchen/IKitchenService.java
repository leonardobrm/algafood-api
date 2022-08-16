package com.algaworks.algafood.domain.service.kitchen;

import com.algaworks.algafood.domain.dto.request.CreateKitchenRequest;
import com.algaworks.algafood.domain.dto.request.UpdatedKitchenRequest;
import com.algaworks.algafood.domain.entities.Kitchen;

import java.util.List;

public interface IKitchenService {
    void create(CreateKitchenRequest request);
    void update(long id, UpdatedKitchenRequest request);
    void delete(long id);
    Kitchen getById(long id);
    List<Kitchen> getAll();
}
