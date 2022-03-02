package com.devsu.gabrielvaldivieso_inventarios.services;

import com.devsu.gabrielvaldivieso_inventarios.models.Client;

public interface ClientService extends CrudService<Client, Long> {

    Client update(Client client);

}
