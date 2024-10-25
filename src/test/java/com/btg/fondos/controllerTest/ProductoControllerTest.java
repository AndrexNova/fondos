package com.btg.fondos.controllerTest;

import com.btg.fondos.controllers.ProductoController;
import com.btg.fondos.exceptions.ProductoNotFoundException;
import com.btg.fondos.services.ProductosServices;
import com.btg.fondos.models.Producto;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ProductoControllerTest {

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductosServices productoService;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto();
        producto.setId("6717d924079bf412d285fd05");
        producto.setNombre("Producto TEST");
        producto.setMontoMinimo(10000);
        producto.setCategoria("FPV");
    }

    @Test
    void testObtenerTodos() {
        List<Producto> productos = Arrays.asList(producto);
        when(productoService.getAll()).thenReturn(productos);
        List<Producto> resultado = productoController.obtenerProductos();
        assertEquals(1, resultado.size());
    }

    @Test
    void testObtenerPorId() {
        when(productoService.getId("6717d924079bf412d285fd05")).thenReturn(producto);
        ResponseEntity<Producto> response = productoController.obtenerProductoPorId("6717d924079bf412d285fd05");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(producto, response.getBody());
    }

    @Test
    void testObtenerPorId_NotFound() {
        when(productoService.getId("2")).thenThrow(new ProductoNotFoundException("2"));
        assertThrows(ProductoNotFoundException.class, () -> productoController.obtenerProductoPorId("2"));
    }

    @Test
    void testCrearProducto() {
        when(productoService.add(producto)).thenReturn(producto);
        Producto response = productoController.agregarProducto(producto);
        assertEquals(producto, response);
    }

    @Test
    void testActualizarProducto() {
        when(productoService.update("6717d924079bf412d285fd05", producto)).thenReturn(producto);
        ResponseEntity<Producto> response = productoController.actualizarProducto("6717d924079bf412d285fd05", producto);
        //assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(producto, response.getBody());
    }

    @Test
    void testEliminarProducto() {
        doNothing().when(productoService).delete("6717d924079bf412d285fd05");
        ResponseEntity<Void> response = productoController.eliminarProducto("6717d924079bf412d285fd05");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
