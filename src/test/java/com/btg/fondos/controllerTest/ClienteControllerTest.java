package com.btg.fondos.controllerTest;


import com.btg.fondos.controllers.ClienteController;
import com.btg.fondos.exceptions.ClienteNotFoundException;
import com.btg.fondos.models.Cliente;
import com.btg.fondos.services.ClientesServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClientesServices clientesService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente();
        cliente.setId("6717d924079bf412d285fd01");
        cliente.setNombre("Cliente TEST");
        cliente.setMonto(50000);
    }

    @Test
    void testObtenerTodos() {
        List<Cliente> clientes = Arrays.asList(cliente);
        when(clientesService.getAll()).thenReturn(clientes);
        List<Cliente> resultado = clienteController.obtenerClientes();
        assertEquals(1, resultado.size());
    }

    @Test
    void testObtenerPorId() {
        when(clientesService.getId("6717d924079bf412d285fd01")).thenReturn(cliente);
        ResponseEntity<Cliente> response = clienteController.obtenerClientePorId("6717d924079bf412d285fd01");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void testObtenerPorId_NotFound() {
        when(clientesService.getId("2")).thenThrow(new ClienteNotFoundException("2"));
        assertThrows(ClienteNotFoundException.class, () -> clienteController.obtenerClientePorId("2"));
    }

    @Test
    void testCrearCliente() {
        when(clientesService.add(cliente)).thenReturn(cliente);
        Cliente response = clienteController.agregarCliente(cliente);
        assertEquals(cliente, response);
    }

    @Test
    void testActualizarCliente() {
        when(clientesService.update("6717d924079bf412d285fd01", cliente)).thenReturn(cliente);
        ResponseEntity<Cliente> response = clienteController.actualizarCliente("6717d924079bf412d285fd01", cliente);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void testEliminarCliente() {
        doNothing().when(clientesService).delete("6717d924079bf412d285fd01");
        ResponseEntity<Void> response = clienteController.eliminarCliente("6717d924079bf412d285fd01");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
