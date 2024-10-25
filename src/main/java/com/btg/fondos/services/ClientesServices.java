package com.btg.fondos.services;

import com.btg.fondos.exceptions.ClienteNotFoundException;
import com.btg.fondos.models.Cliente;
import com.btg.fondos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getId(String id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
    }

    public Cliente add(Cliente cliente) {

        if(cliente.getMonto() == 0){
            cliente.setMonto(500000);
        }
        return clienteRepository.save(cliente);
    }

    public Cliente update(String id, Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public void delete(String id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException(id);
        }
        clienteRepository.deleteById(id);
    }





}
