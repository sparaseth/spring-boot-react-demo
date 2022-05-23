package com.skptech.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.skptech.demo.model.Client;
import com.skptech.demo.service.ClientsService;

@RestController
@RequestMapping("api/clients")
@AllArgsConstructor
public class ClientsController {

    private ClientsService clientsService;

    @GetMapping
    public List<Client> getClients() {
        return clientsService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        return clientsService.getClient(id);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody Client client) throws URISyntaxException {
        Client savedClient = clientsService.saveClient(client);
        return ResponseEntity.created(new URI("/clients/" + savedClient.getId())).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Client client) {
        Client updatedClient = clientsService.updateClient(id, client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientsService.deleteClient(id);
        return ResponseEntity.ok().build();
    }

}
