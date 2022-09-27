package com.algaworks.algafood.domain.usecases.state;

import com.algaworks.algafood.domain.dto.request.state.CreateStateRequest;
import com.algaworks.algafood.domain.dto.request.state.UpdateStateRequest;
import com.algaworks.algafood.domain.model.State;

import java.util.List;

public interface IStateService {
    void create(final CreateStateRequest request);

    List<State> findAll();

    State findById(final Long id);

    void update(final Long id, UpdateStateRequest request);

    void delete(final Long id);
}
