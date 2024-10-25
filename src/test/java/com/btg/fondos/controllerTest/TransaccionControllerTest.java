package com.btg.fondos.controllerTest;

import com.btg.fondos.controllers.TransaccionesController;
import com.btg.fondos.dto.TransaccionDTO;
import com.btg.fondos.exceptions.TransaccionesNotFoundException;
import com.btg.fondos.models.ClienteProducto;
import com.btg.fondos.models.Transaccion;
import com.btg.fondos.services.TransaccionesServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class TransaccionControllerTest {

    @InjectMocks
    private TransaccionesController transaccionesController;

    @Mock
    private TransaccionesServices transaccionesServices;

    private Transaccion transaccion;

    private ClienteProducto clienteProducto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Transaccion transaccion = new Transaccion();
        transaccion.setId("671a68b52fa90327e12428ba");
        ClienteProducto clienteProducto = new ClienteProducto();
        clienteProducto.setEstado(true);
        clienteProducto.setMontoInversion(100000);
        clienteProducto.setIdCliente("6717d924079bf412d285fd01");
        clienteProducto.setMontoInversion(300000);
        clienteProducto.setIdProducto("6717d924079bf412d285fd05");
        transaccion.setClienteProducto(clienteProducto);

    }

    @Test
    void testObtenerTodos() {
        List<Transaccion> transacciones = Arrays.asList(transaccion);
        //when(transaccionesServices.getAll()).thenReturn(transacciones);
        //List<TransaccionDTO> resultado = transaccionesController.obtenerTransacciones();
        //assertNotEquals(0, resultado.size());
    }

    @Test
    void testObtenerPorId() {
        when(transaccionesServices.getId("671a68b52fa90327e12428ba")).thenReturn(transaccion);
        ResponseEntity<Transaccion> response = transaccionesController.obtenerTransaccionesPorId("671a68b52fa90327e12428ba");
        //assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaccion, response.getBody());
    }

    @Test
    void testObtenerPorId_NotFound() {
        when(transaccionesServices.getId("2")).thenThrow(new TransaccionesNotFoundException("2"));
        assertThrows(TransaccionesNotFoundException.class, () -> transaccionesController.obtenerTransaccionesPorId("2"));
    }

    @Test
    void testCrearProducto() {
        when(transaccionesServices.add(transaccion)).thenReturn(transaccion);
        Transaccion response = transaccionesController.agregarTransaccion(transaccion);
        assertEquals(transaccion, response);
    }

    @Test
    void testActualizarProducto() {
        when(transaccionesServices.update("6717d924079bf412d285fd01", transaccion)).thenReturn(transaccion);
        ResponseEntity<Transaccion> response = transaccionesController.actualizarTransaccion("6717d924079bf412d285fd01", transaccion);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaccion, response.getBody());
    }

    @Test
    void testEliminarProducto() {
        doNothing().when(transaccionesServices).delete("6717d924079bf412d285fd01");
        ResponseEntity<Void> response = transaccionesController.eliminarTransaccion("6717d924079bf412d285fd01");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
