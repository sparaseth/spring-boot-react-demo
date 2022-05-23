package com.skptech.demo.service;

import com.skptech.demo.model.Client;
import com.skptech.demo.repository.ClientsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClientsService {

    private final ClientsRepository clientsRepository;

    public List<Client> getAllClients() {
        return clientsRepository.findAll();
    }

    public Client getClient(Long id) {
        return clientsRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Client saveClient(Client client) {
        return clientsRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientsRepository.deleteById(id);
    }

    public Client updateClient(Long id, Client client) {
        Client currentClient = this.getClient(id);
        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());
        return this.saveClient(currentClient);
    }
}
