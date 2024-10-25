package com.btg.fondos.controllers;

import com.btg.fondos.Request.ClienteProductoResponse;
import com.btg.fondos.aws.EmailService;
import com.btg.fondos.aws.SmsService;
import com.btg.fondos.dto.ClienteProductoDTO;
import com.btg.fondos.models.Transaccion;
import com.btg.fondos.models.Cliente;
import com.btg.fondos.models.ClienteProducto;
import com.btg.fondos.models.Producto;
import com.btg.fondos.services.ClienteProductosServices;
import com.btg.fondos.services.ClientesServices;
import com.btg.fondos.services.ProductosServices;
import com.btg.fondos.services.TransaccionesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clientesproductos")
public class ClienteProductosController {

    @Autowired
    private ClienteProductosServices clienteProductosServices;
    @Autowired
    private ClientesServices clientesServices;
    @Autowired
    private ProductosServices productosServices;
    @Autowired
    private TransaccionesServices transaccionesServices;

    private final SmsService smsService;

    private final EmailService emailService;

    public ClienteProductosController(SmsService smsService, EmailService emailService) {
        this.smsService = smsService;
        this.emailService = emailService;
    }

    // Obtener todos los clientes
    @GetMapping
    public List<ClienteProducto> obtenerClientesProductos() {
        return clienteProductosServices.getAll();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteProducto> obtenerClienteProductosPorId(@PathVariable String id) {
        ClienteProducto clienteProductos = clienteProductosServices.getId(id);
        return clienteProductos != null ? ResponseEntity.ok(clienteProductos) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<?> agregarClienteProductos(@RequestBody ClienteProductoDTO clienteProductos) {
        Cliente clienteUpdate = clientesServices.getId(clienteProductos.getIdCliente());
        Producto producto = productosServices.getId(clienteProductos.getIdProducto());

        // Verificar si el cliente tiene suficiente monto
        if (clienteProductos.getMontoInversion() < producto.getMontoMinimo()) {
            // Lanzar error si no tiene fondos suficientes
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El valor de la inversion debe ser mayor a "+ producto.getMontoMinimo());
        }

        int newMonto = clienteUpdate.getMonto() - clienteProductos.getMontoInversion();

        // Verificar si el cliente tiene suficiente monto
        if (newMonto < 0) {
            // Lanzar error si no tiene fondos suficientes
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No tiene saldo disponible para vincularse al fondo "+producto.getNombre());
        }

        clienteUpdate.setMonto(newMonto);
        Cliente newCliente = clientesServices.update(clienteProductos.getIdCliente(), clienteUpdate);


        ClienteProducto clienteProductoOBJ = new ClienteProducto();

        clienteProductoOBJ.setIdProducto(clienteProductos.getIdProducto());
        clienteProductoOBJ.setIdCliente(clienteProductos.getIdCliente());
        clienteProductoOBJ.setId(clienteProductos.getId());
        clienteProductoOBJ.setMontoInversion(clienteProductos.getMontoInversion());
        clienteProductoOBJ.setEstado(clienteProductos.isEstado());

        ClienteProducto newClienteProductos = clienteProductosServices.add(clienteProductoOBJ);

        Transaccion transaccion = new Transaccion();
        transaccion.setClienteProducto(newClienteProductos);
        transaccionesServices.add(transaccion);


        String mensaje= "Se suscribio el fondo " + producto.getNombre() +
                " con " + newClienteProductos.getMontoInversion();
        String asunto = "Apertura de fondo";

        if(clienteProductos.getOpcionEnvio() == 0){
            smsService.sendSMS(clienteUpdate.getNumeroTelefonico(), mensaje);
        }else{
            emailService.sendEmail(clienteUpdate.getEmail(), asunto, mensaje);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newClienteProductos);
    }

    @PutMapping("/estado")
    public ResponseEntity<?> actualizarClienteProductos(@RequestBody ClienteProductoDTO clienteProducto) {


        //ClienteProducto clienteProducto = clienteProductosServices.getId(clienteProductos.getId());

        clienteProducto.setEstado(!clienteProducto.isEstado());

        Cliente clienteUpdate = clientesServices.getId(clienteProducto.getIdCliente());
        Producto producto = productosServices.getId(clienteProducto.getIdProducto());

        int newMonto = clienteUpdate.getMonto();

        if (clienteProducto.isEstado()) {
            newMonto = newMonto - clienteProducto.getMontoInversion();
        } else {
            newMonto = newMonto + clienteProducto.getMontoInversion();
        }

        // Verificar si el cliente tiene suficiente monto
        if (newMonto < 0) {
            // Lanzar error si no tiene fondos suficientes
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No tiene saldo disponible para vincularse al fondo "+producto.getNombre());
        }


        clienteUpdate.setMonto(newMonto);
        if(!clienteProducto.isEstado()){
            clienteProducto.setMontoInversion(0);
        }
        Cliente newCliente = clientesServices.update(clienteProducto.getIdCliente(), clienteUpdate);

        ClienteProducto clienteProductoOBJ = new ClienteProducto();

        clienteProductoOBJ.setIdProducto(clienteProducto.getIdProducto());
        clienteProductoOBJ.setIdCliente(clienteProducto.getIdCliente());
        clienteProductoOBJ.setId(clienteProducto.getId());
        clienteProductoOBJ.setMontoInversion(clienteProducto.getMontoInversion());
        clienteProductoOBJ.setEstado(clienteProducto.isEstado());

        ClienteProducto clienteProductosUpdate = clienteProductosServices.update(clienteProducto.getId(), clienteProductoOBJ);

        Transaccion transaccion = new Transaccion();
        transaccion.setClienteProducto(clienteProductosUpdate);
        transaccionesServices.add(transaccion);


        if(clienteProducto.isEstado()) {

            String mensaje= "Se suscribio el fondo " + producto.getNombre() +
                    " con " + clienteProductosUpdate.getMontoInversion();
            String asunto = "Apertura de fondo";

            if(clienteProducto.getOpcionEnvio() == 0){
                smsService.sendSMS(clienteUpdate.getNumeroTelefonico(), mensaje);
            }else{
                emailService.sendEmail(clienteUpdate.getEmail(), asunto, mensaje);
            }
        }

        return ResponseEntity.ok(clienteProductosUpdate);
    }

    // Actualizar un cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteProducto> actualizarClienteProductos(@PathVariable String id, @RequestBody ClienteProducto clienteProductos) {
        ClienteProducto clienteProductosUpdate = clienteProductosServices.update(id, clienteProductos);
        return ResponseEntity.ok(clienteProductosUpdate);
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarClienteProductos(@PathVariable String id) {
        clienteProductosServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todos los clientes
    @GetMapping("/fondos/{idCliente}")
    public List<ClienteProductoResponse> obetenerFondosClientesProductos(@PathVariable String idCliente) {

        List<ClienteProducto> clienteProductos = clienteProductosServices.getAll();
        List<Producto> productos = productosServices.getAll();

        List<ClienteProductoResponse> resultado = new ArrayList<>();

        for (Producto producto : productos) {
            Cliente cliente = clientesServices.getId(idCliente);
            ClienteProductoResponse dto = new ClienteProductoResponse(cliente, producto);
            dto.setMontoMinimo(producto.getMontoMinimo());

            for (ClienteProducto cp : clienteProductos) {
                if (cp.getIdCliente().equals(idCliente) && cp.getIdProducto().equals(producto.getId())) {
                    dto.setEstado(cp.isEstado());
                    dto.setId(cp.getId());
                    dto.setMontoInversion(cp.getMontoInversion());
                }
            }
            resultado.add(dto);

        }

        return resultado;
    }
}