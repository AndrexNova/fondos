package com.btg.fondos.infrastructure.adapter.in;

import com.btg.fondos.domain.models.Cliente;
import com.btg.fondos.application.services.ClientesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClientesServices clientesServices;

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> obtenerClientes() {
        return clientesServices.getAll();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable String id) {
        Cliente cliente = clientesServices.getId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo cliente
    @PostMapping
    public Cliente agregarCliente(@RequestBody Cliente cliente) {
        Cliente newCliente = clientesServices.add(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCliente).getBody();
    }

    // Actualizar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String id, @RequestBody Cliente cliente) {
        Cliente clienteUpdate = clientesServices.update(id, cliente);
        return ResponseEntity.ok(clienteUpdate);
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String id) {
        clientesServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
