package com.algaworks.algafood.domain.useCases.kitchen;

import com.algaworks.algafood.domain.entities.Kitchen;

import java.util.List;

public interface IKitchenUseCase {
    void create(String name);
    void update(long id, String name);
    void delete(long id);
    Kitchen getById(long id);
    List<Kitchen> getAll();
}
