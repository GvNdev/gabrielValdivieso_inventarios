package com.devsu.gabrielvaldivieso_inventarios.services.sdjpa;

import com.devsu.gabrielvaldivieso_inventarios.models.Client;
import com.devsu.gabrielvaldivieso_inventarios.repositories.ClientRepository;
import com.devsu.gabrielvaldivieso_inventarios.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientSDJPAService implements ClientService {

    private final ClientRepository clientRepository;

    public ClientSDJPAService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<Client> findAll() {
        Set<Client> clients = new HashSet<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public Client findById(Long aLong) {
        return clientRepository.findById(aLong).orElse(null);
    }

    @Override
    public Client save(Client object) {
        return clientRepository.save(object);
    }

    @Override
    public Client update(Client object) {
        if (!clientRepository.findById(object.getId()).isEmpty()) {
            return clientRepository.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Client object) {
        clientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }
}
