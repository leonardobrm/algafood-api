package com.algaworks.algafood.infrastructure.api.controllers;

import com.algaworks.algafood.domain.dto.request.kitchen.CreateKitchenRequest;
import com.algaworks.algafood.domain.dto.request.kitchen.UpdatedKitchenRequest;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.usecases.kitchen.IKitchenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/kitchens")
public class KitchenController {
    private final IKitchenService kitchenUseCases;

    @PostMapping
    public ResponseEntity<Kitchen> post(@Validated @RequestBody CreateKitchenRequest request) {
        this.kitchenUseCases.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Kitchen>> getAll() {
        List<Kitchen> kitchens = this.kitchenUseCases.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(kitchens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> getById(@Validated @PathVariable long id) {
        Kitchen kitchen = this.kitchenUseCases.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> put(@Validated @PathVariable long id, @Validated @RequestBody UpdatedKitchenRequest request) {
        this.kitchenUseCases.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kitchen> delete(@Validated @PathVariable long id) {
        this.kitchenUseCases.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
