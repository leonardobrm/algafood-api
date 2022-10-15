package com.algaworks.algafood.infrastructure.api.controllers;

import com.algaworks.algafood.core.dto.request.state.CreateStateRequest;
import com.algaworks.algafood.core.dto.request.state.UpdateStateRequest;
import com.algaworks.algafood.core.model.State;
import com.algaworks.algafood.core.usecases.state.IStateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/states")
public class StateController {

    private final IStateService stateUseCases;
    
    @PostMapping
    public ResponseEntity post(@RequestBody CreateStateRequest request) {
        this.stateUseCases.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<State>> getAll() {
        List<State> states = this.stateUseCases.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(states);
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getById(@Validated @PathVariable Long id) {
        State state = this.stateUseCases.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(state);
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@Validated @PathVariable Long id, @RequestBody UpdateStateRequest request) {
        this.stateUseCases.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Validated @PathVariable Long id) {
        this.stateUseCases.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
