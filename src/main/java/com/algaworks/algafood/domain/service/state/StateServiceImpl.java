package com.algaworks.algafood.domain.service.state;

import com.algaworks.algafood.domain.dto.request.state.CreateStateRequest;
import com.algaworks.algafood.domain.dto.request.state.UpdateStateRequest;
import com.algaworks.algafood.domain.entities.State;
import com.algaworks.algafood.domain.exception.errors.ApiException;
import com.algaworks.algafood.domain.repository.city.ICityRepository;
import com.algaworks.algafood.domain.repository.state.IStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StateServiceImpl implements IStateService {

    private final IStateRepository stateRepository;
    private final ICityRepository cityRepository;

    public StateServiceImpl(IStateRepository stateRepository, ICityRepository cityRepository) {
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void create(CreateStateRequest request) {
        Optional<State> verifyStateExists = this.stateRepository.findByName(request.getName());

        if(verifyStateExists.isPresent())  throw new ApiException("State already exists", HttpStatus.BAD_REQUEST);

        State newState = new State(request.getName(), request.getUf());

        this.stateRepository.save(newState);
        log.info("State created successfully");
    }

    @Override
    public List<State> findAll() {
        return this.stateRepository.findAll();
    }

    @Override
    public State findById(Long id) {
        return this.stateRepository.findById(id).orElseThrow(() -> {
             throw new ApiException("State not found", HttpStatus.NOT_FOUND);
         });
    }

    @Override
    public void update(Long id, UpdateStateRequest request) {
        State verifyStateExists = this.stateRepository.findById(id).orElseThrow(() -> {
            throw new ApiException("State not found", HttpStatus.NOT_FOUND);
        });

        String name = request.getName() != null && request.getName() != verifyStateExists.getName() ? request.getName()
                : verifyStateExists.getName();

        String uf = request.getUf() != null && request.getUf() != verifyStateExists.getUf() ? request.getUf()
                : verifyStateExists.getUf();

        State newState = new State(name, uf);
        BeanUtils.copyProperties(newState,  verifyStateExists, "id");

        this.stateRepository.save(verifyStateExists);
        log.info("state updated successfully");
    }

    @Override
    public void delete(Long id) {
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
