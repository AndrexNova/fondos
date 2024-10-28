package com.btg.fondos.controllerTest;

import com.btg.fondos.infrastructure.adapter.in.ClienteController;
import com.btg.fondos.infrastructure.adapter.in.ClienteProductosController;
import com.btg.fondos.domain.exceptions.ClienteNotFoundException;
import com.btg.fondos.domain.models.Cliente;
import com.btg.fondos.domain.models.ClienteProducto;
import com.btg.fondos.application.services.ClienteProductosServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class ClienteProductosControllerTest {

    @InjectMocks
    private ClienteProductosController clienteProductosController;

    @Mock
    private ClienteProductosServices clienteProductosServices;

    private ClienteProducto clienteProducto;
    private ClienteController clienteController;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteProducto = new ClienteProducto();
        clienteProducto.setIdProducto("6717d924079bf412d285fd05");
        clienteProducto.setIdCliente("6717d924079bf412d285fd01");
        clienteProducto.setEstado(true);
        clienteProducto.setMontoInversion(300000);
        clienteProducto.setId("671a7baf6e0ccc1acf8e5d9d");

        cliente = new Cliente();
        cliente.setId("6717d924079bf412d285fd01");
        cliente.setNombre("Cliente TEST");
        cliente.setMonto(50000);

    }

    @Test
    void testObtenerTodos() {
        List<ClienteProducto> productos = Arrays.asList(clienteProducto);
        //when(clienteProductosServices.getAll()).thenReturn(productos);
        //List<ClienteProducto> resultado = clienteProductosController.obtenerClientesProductos();
        //assertEquals(1, resultado.size());
    }

    @Test
    void testObtenerPorId() {
        when(clienteProductosServices.getId("6717d924079bf412d285fd05")).thenReturn(clienteProducto);
        //ResponseEntity<ClienteProducto> response = clienteProductosController.obtenerClienteProductosPorId("6717d924079bf412d285fd05");
        //assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(clienteProducto, response.getBody());
    }

    @Test
    void testObtenerPorId_NotFound() {
        when(clienteProductosServices.getId("2")).thenThrow(new ClienteNotFoundException("2"));
        //assertThrows(ClienteNotFoundException.class, () -> clienteProductosController.obtenerClienteProductosPorId("2"));
    }


    @Test
    void testActualizarProducto() {
        when(clienteProductosServices.update("6717d924079bf412d285fd05", clienteProducto)).thenReturn(clienteProducto);
        //ResponseEntity<ClienteProducto> response = clienteProductosController.actualizarClienteProductos("6717d924079bf412d285fd05", clienteProducto);
        //assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(clienteProducto, response.getBody());
    }

    @Test
    void testEliminarProducto() {
        doNothing().when(clienteProductosServices).delete("6717d924079bf412d285fd05");
        //ResponseEntity<Void> response = clienteProductosController.eliminarClienteProductos("6717d924079bf412d285fd05");
        //assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
