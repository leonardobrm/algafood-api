package com.algaworks.algafood.core.usecases.state;

import com.algaworks.algafood.core.dto.request.state.CreateStateRequest;
import com.algaworks.algafood.core.dto.request.state.UpdateStateRequest;
import com.algaworks.algafood.core.model.State;

import java.util.List;

public interface IStateService {
    void create(final CreateStateRequest request);

    List<State> findAll();

    State findById(final Long id);

    void update(final Long id, UpdateStateRequest request);

    void delete(final Long id);
}
