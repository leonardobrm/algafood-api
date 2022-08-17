package com.algaworks.algafood.api.v1.controllers;

import com.algaworks.algafood.domain.dto.request.state.CreateStateRequest;
import com.algaworks.algafood.domain.dto.request.state.UpdateStateRequest;
import com.algaworks.algafood.domain.entities.State;
import com.algaworks.algafood.domain.service.state.IStateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/states")
public class StateController {

    private final IStateService stateService;

    public StateController(IStateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CreateStateRequest request){
        this.stateService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<State>>findAll(){
        List<State> states = this.stateService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(states);
    }

    @GetMapping("/{id}")
    public ResponseEntity<State>findById(@Validated @PathVariable Long id){
        State state = this.stateService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(state);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@Validated @PathVariable Long id, @RequestBody UpdateStateRequest request){
        this.stateService.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Validated @PathVariable Long id){
        this.stateService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
