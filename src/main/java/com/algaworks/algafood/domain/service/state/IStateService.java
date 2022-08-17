package com.algaworks.algafood.domain.service.state;

import com.algaworks.algafood.domain.dto.request.state.CreateStateRequest;
import com.algaworks.algafood.domain.dto.request.state.UpdateStateRequest;
import com.algaworks.algafood.domain.entities.State;

import java.util.List;

public interface IStateService {
    void create(CreateStateRequest request);
    List<State> findAll();
    State findById(Long id);
    void update(Long id, UpdateStateRequest request);
    void delete(Long id);
}
