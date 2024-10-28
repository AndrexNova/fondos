package com.btg.fondos.infrastructure.adapter.in;

import com.btg.fondos.domain.port.dto.TransaccionDTO;
import com.btg.fondos.domain.models.Cliente;
import com.btg.fondos.domain.models.Producto;
import com.btg.fondos.domain.models.Transaccion;
import com.btg.fondos.domain.port.outbound.ClienteRepository;
import com.btg.fondos.domain.port.outbound.ProductoRepository;
import com.btg.fondos.application.services.TransaccionesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/transacciones")
public class TransaccionesController {

    @Autowired
    private TransaccionesServices transaccionesServices;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los transacciones
    @GetMapping
    public List<TransaccionDTO> obtenerTransacciones() {

        List<Transaccion> transacciones = transaccionesServices.getAll();

        // Crear un DTO para devolver tanto las transacciones como los datos del cliente
        List<TransaccionDTO> resultado = new ArrayList<>();
        if(transacciones.size() > 0) {
            for (Transaccion transaccion : transacciones) {
                // Obtener el cliente usando el idCliente almacenado en la transacción
                Cliente cliente = clienteRepository.findById(transaccion.getClienteProducto().getIdCliente()).orElse(null);
                Producto producto = productoRepository.findById(transaccion.getClienteProducto().getIdProducto()).orElse(null);
                // Crear el DTO combinando
                TransaccionDTO dto = new TransaccionDTO(transaccion, cliente, producto);
                resultado.add(dto);
            }
        }

        return resultado;
    }

    // Obtener un transaccion por ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> obtenerTransaccionesPorId(@PathVariable String id) {
        Transaccion transaccion = transaccionesServices.getId(id);
        return transaccion != null ? ResponseEntity.ok(transaccion) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo transaccion
    @PostMapping
    public Transaccion agregarTransaccion(@RequestBody Transaccion transaccion) {
        Transaccion newTransaccion = transaccionesServices.add(transaccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaccion).getBody();
    }

    // Actualizar un transaccion
    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> actualizarTransaccion(@PathVariable String id, @RequestBody Transaccion transaccion) {
        Transaccion transaccionUpdate = transaccionesServices.update(id, transaccion);
        return ResponseEntity.ok(transaccionUpdate);
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTransaccion(@PathVariable String id) {
        transaccionesServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
