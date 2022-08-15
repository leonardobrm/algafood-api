package com.algaworks.algafood.domain.useCases.kitchen;

import com.algaworks.algafood.domain.entities.Kitchen;
import com.algaworks.algafood.domain.repository.kitchen.IKitchenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class KitchenUseCaseImpl implements IKitchenUseCase {

    private final IKitchenRepository kitchenRepository;

    public KitchenUseCaseImpl(IKitchenRepository kitchenRepository) {
        this.kitchenRepository = kitchenRepository;
    }
    @Override
    public void create(String name) {
        var kitchenAlreadyExists = this.kitchenRepository.findByName(name);

        if (!kitchenAlreadyExists.isEmpty()) {
            String MESSAGE_ERROR = "Kitchen already exists";
            log.error(MESSAGE_ERROR);
            throw new Error(MESSAGE_ERROR);
        }
        this.kitchenRepository.save(new Kitchen(name));
        log.info("Kitchen created successufully");
    }
    @Override
    public void update(long id, String name) {
        var findKitchenExists = kitchenRepository.findById(id);

        if (findKitchenExists.isPresent()) {
            findKitchenExists.get().updatedName(name);
            kitchenRepository.save(findKitchenExists.get());
            log.info("Kitchen updated successfully");
        }
    }

    @Override
    public void delete(long id) {
        var findKitchenExists = kitchenRepository.findById(id);

        if(findKitchenExists.isPresent()){
            kitchenRepository.delete(findKitchenExists.get());
            log.info("Kitchen deleted successfully");
        }
    }

    @Override
    public Kitchen getById(long id) {
        Optional<Kitchen> findKitchenExists = this.kitchenRepository.findById(id);

        return findKitchenExists.orElse(null);
    }

    @Override
    public List<Kitchen> getAll() {
        return this.kitchenRepository.findAll();
    }
}
