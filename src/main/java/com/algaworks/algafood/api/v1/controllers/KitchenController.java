package com.algaworks.algafood.api.v1.controllers;

import com.algaworks.algafood.domain.dto.request.kitchen.CreateKitchenRequest;
import com.algaworks.algafood.domain.dto.request.kitchen.UpdatedKitchenRequest;
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
    private final IKitchenService kitchenService;

    public KitchenController(IKitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @PostMapping
    public ResponseEntity<Kitchen> create(@Validated @RequestBody CreateKitchenRequest request) {
        this.kitchenService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Kitchen>> findAll() {
        List<Kitchen> kitchens = this.kitchenService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(kitchens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> findById(@Validated @PathVariable long id) {
        Kitchen kitchen = this.kitchenService.findById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(kitchen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> update(@Validated @PathVariable long id, @Validated @RequestBody UpdatedKitchenRequest request) {
        this.kitchenService.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kitchen> delete(@Validated @PathVariable long id){
        this.kitchenService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
