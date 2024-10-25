package com.btg.fondos.servicesTest;

import com.btg.fondos.exceptions.ProductoNotFoundException;
import com.btg.fondos.models.Producto;
import com.btg.fondos.repository.ProductoRepository;
import com.btg.fondos.services.ProductosServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    @InjectMocks
    private ProductosServices productoService;

    @Mock
    private ProductoRepository productoRepository;

    private Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto();
        producto.setId("6717d924079bf412d285fd01");
        producto.setNombre("Producto TEST");
        producto.setMontoMinimo(10000);
        producto.setCategoria("FPV");
    }

    @Test
    void testCrearProducto() {
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        Producto nuevoProducto = productoService.add(producto);
        assertNotNull(nuevoProducto);
        assertEquals("Producto TEST", nuevoProducto.getNombre());
    }

    @Test
    void testObtenerPorId() {
        when(productoRepository.findById("6717d924079bf412d285fd01")).thenReturn(Optional.of(producto));
        Producto encontrado = productoService.getId("6717d924079bf412d285fd01");
        assertEquals("Producto TEST", encontrado.getNombre());
    }

    @Test
    void testObtenerPorId_NotFound() {
        when(productoRepository.findById("2")).thenReturn(Optional.empty());
        assertThrows(ProductoNotFoundException.class, () -> productoService.getId("2"));
    }

    @Test
    void testActualizarProducto() {
        when(productoRepository.existsById("6717d924079bf412d285fd01")).thenReturn(true);
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        Producto actualizado = productoService.update("6717d924079bf412d285fd01", producto);
        assertEquals("Producto TEST", actualizado.getNombre());
    }

    @Test
    void testEliminarProducto() {
        when(productoRepository.existsById("6717d924079bf412d285fd01")).thenReturn(true);
        doNothing().when(productoRepository).deleteById("6717d924079bf412d285fd01");
        productoService.delete("6717d924079bf412d285fd01");
        verify(productoRepository, times(1)).deleteById("6717d924079bf412d285fd01");
    }


}
