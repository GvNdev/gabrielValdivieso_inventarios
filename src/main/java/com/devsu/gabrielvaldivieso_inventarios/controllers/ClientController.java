package com.devsu.gabrielvaldivieso_inventarios.controllers;

import com.devsu.gabrielvaldivieso_inventarios.models.Client;
import com.devsu.gabrielvaldivieso_inventarios.services.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Value("${client.success.delete}")
    private String successDelete;

    @Value("${client.error.notFound}")
    private String errorNotFound;

    @Value("${client.error.badRequest}")
    private String errorBadRequest;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        Map<String, Object> data = new HashMap<>();
        Set<Client> clients = clientService.findAll();
        if (clients == null) {
            data.put("message", errorNotFound);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Client client) {
        if (client.getIdentification() == null || client.getName() == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("message", errorBadRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }

        Client newClient = clientService.save(client);
        return ResponseEntity.status(HttpStatus.OK).body(newClient);
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Client client) {
        Map<String, Object> data = new HashMap<>();

        if (client.getId() == null || client.getIdentification() == null || client.getName() == null) {
            data.put("message", errorBadRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }

        Client newClient = clientService.update(client);

        if (newClient == null) {
            data.put("message", errorNotFound);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }

        return ResponseEntity.status(HttpStatus.OK).body(newClient);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Client client) {
        Map<String, Object> data = new HashMap<>();

        if (client.getId() == null) {
            data.put("message", errorBadRequest);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }

        try {
            clientService.deleteById(client.getId());
            data.put("message", successDelete);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            data.put("message", errorNotFound);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
    }
}
