package com.algaworks.algafood.domain.usecases.state;

import com.algaworks.algafood.domain.dto.request.state.CreateStateRequest;
import com.algaworks.algafood.domain.dto.request.state.UpdateStateRequest;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.infrastructure.exception.errors.ApiException;
import com.algaworks.algafood.infrastructure.repository.city.ICityRepository;
import com.algaworks.algafood.infrastructure.repository.state.IStateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class StateServiceImpl implements IStateService {

    private final IStateRepository stateRepository;
    private final ICityRepository cityRepository;

    @Override
    public void create(final CreateStateRequest request) {
        Optional<State> verifyStateExists = this.stateRepository.findByName(request.name());

        if (verifyStateExists.isPresent()) throw new ApiException("State already exists", HttpStatus.BAD_REQUEST);

        State newState = new State(request.name(), request.uf());

        this.stateRepository.save(newState);
        log.info("State created successfully");
    }

    @Override
    public List<State> findAll() {
        return this.stateRepository.findAll();
    }

    @Override
    public State findById(final Long id) {
        return this.stateRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("State not found", HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public void update(final Long id, final UpdateStateRequest request) {
        State verifyStateExists = this.stateRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("State not found", HttpStatus.NOT_FOUND);
        });

        String name = request.name() != null && request.name() != verifyStateExists.getName() ? request.name()
                : verifyStateExists.getName();

        String uf = request.uf() != null && request.uf() != verifyStateExists.getUf() ? request.uf()
                : verifyStateExists.getUf();

        State newState = new State(name, uf);
        BeanUtils.copyProperties(newState, verifyStateExists, "id");

        this.stateRepository.save(verifyStateExists);
        log.info("state updated successfully");
    }

    @Override
    public void delete(final Long id) {
        State findStateExists = this.stateRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("state not found", HttpStatus.NOT_FOUND);
        });

        //validated if there is a restaurant using this kitchen
        this.cityRepository.findByStateId(findStateExists.getId()).ifPresent(city -> {
            if (city.size() > 0) {
                throw new ApiException("There is a city using the state", HttpStatus.CONFLICT);
            }
        });
        this.stateRepository.delete(findStateExists);
        log.info("State deleted successfully");
    }
}
