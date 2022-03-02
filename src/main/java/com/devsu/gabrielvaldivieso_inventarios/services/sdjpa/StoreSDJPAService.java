package com.devsu.gabrielvaldivieso_inventarios.services.sdjpa;

import com.devsu.gabrielvaldivieso_inventarios.models.Store;
import com.devsu.gabrielvaldivieso_inventarios.repositories.StoreRepository;
import com.devsu.gabrielvaldivieso_inventarios.services.StoreService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StoreSDJPAService implements StoreService {

    private final StoreRepository storeRepository;

    public StoreSDJPAService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Set<Store> findAll() {
        Set<Store> stores = new HashSet<>();
        storeRepository.findAll().forEach(stores::add);
        return stores;
    }

    @Override
    public Store findById(Long aLong) {
        return storeRepository.findById(aLong).orElse(null);
    }

    @Override
    public Store save(Store object) {
        return storeRepository.save(object);
    }

    @Override
    public void delete(Store object) {
        storeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        storeRepository.deleteById(aLong);
    }
}
