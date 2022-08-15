package com.algaworks.algafood.domain.controllers;

import com.algaworks.algafood.domain.entities.Kitchen;
import com.algaworks.algafood.domain.useCases.kitchen.IKitchenUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/kitchen")
public class KitchenController {
    private final IKitchenUseCase kitchenUseCase;

    public KitchenController(IKitchenUseCase kitchenUseCase) {
        this.kitchenUseCase = kitchenUseCase;
    }

    @PostMapping
    public void create(@RequestParam String name) {
        this.kitchenUseCase.create(name);
    }

    @GetMapping
    public List<Kitchen> listAll() {
        return this.kitchenUseCase.getAll();
    }

    @GetMapping("/{id}")
    public Kitchen getById(@PathVariable long id) {
        return this.kitchenUseCase.getById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestParam String name) {
        this.kitchenUseCase.update(id, name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        this.kitchenUseCase.delete(id);
    }
}
