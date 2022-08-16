package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.domain.dto.request.CreateKitchenRequest;
import com.algaworks.algafood.domain.dto.request.UpdatedKitchenRequest;
import com.algaworks.algafood.domain.entities.Kitchen;
import com.algaworks.algafood.domain.service.kitchen.IKitchenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/kitchens")
public class KitchenController {
    private final IKitchenService kitchenUseCase;

    public KitchenController(IKitchenService kitchenUseCase) {
        this.kitchenUseCase = kitchenUseCase;
    }

    @PostMapping
    public ResponseEntity<Kitchen> create(@Validated @RequestBody CreateKitchenRequest request) {
        this.kitchenUseCase.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Kitchen>> listAll() {
        List<Kitchen> kitchens = this.kitchenUseCase.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(kitchens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> getById(@Validated @PathVariable long id) {
        Kitchen kitchen = this.kitchenUseCase.getById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> update(@Validated @PathVariable long id, @Validated @RequestBody UpdatedKitchenRequest request) {
        this.kitchenUseCase.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kitchen> delete(@Validated @PathVariable long id){
        this.kitchenUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
