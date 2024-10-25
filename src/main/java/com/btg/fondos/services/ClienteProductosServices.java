package com.btg.fondos.services;

import com.btg.fondos.exceptions.ClienteProductoNotFoundException;
import com.btg.fondos.exceptions.ClienteNotFoundException;
import com.btg.fondos.exceptions.ProductoNotFoundException;
import com.btg.fondos.models.ClienteProducto;
import com.btg.fondos.repository.ClienteProductoRepository;
import com.btg.fondos.repository.ClienteRepository;
import com.btg.fondos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteProductosServices {

    @Autowired
    private ClienteProductoRepository clienteProductoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public List<ClienteProducto> getAll() {
        return clienteProductoRepository.findAll();
    }

    public ClienteProducto getId(String id) {
        return clienteProductoRepository.findById(id).orElseThrow(() -> new ClienteProductoNotFoundException(id));
    }

    public ClienteProducto add(ClienteProducto clienteProducto) {

        if (!clienteRepository.existsById(clienteProducto.getIdCliente())) {
            throw new ClienteNotFoundException(clienteProducto.getIdCliente());
        }

        if (!productoRepository.existsById(clienteProducto.getIdProducto())) {
            throw new ProductoNotFoundException(clienteProducto.getIdCliente());
        }
        clienteProducto.setEstado(true);
        return clienteProductoRepository.save(clienteProducto);
    }

    public ClienteProducto update(String id, ClienteProducto clienteProducto) {
        if (!clienteProductoRepository.existsById(id)) {
            throw new ClienteProductoNotFoundException(id);
        }
        clienteProducto.setId(id);
        return clienteProductoRepository.save(clienteProducto);
    }

    public void delete(String id) {
        if (!clienteProductoRepository.existsById(id)) {
            throw new ClienteProductoNotFoundException(id);
        }
        clienteProductoRepository.deleteById(id);
    }

}
