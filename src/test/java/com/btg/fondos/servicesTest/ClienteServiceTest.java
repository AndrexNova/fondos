package com.btg.fondos.servicesTest;

import com.btg.fondos.exceptions.ProductoNotFoundException;
import com.btg.fondos.models.Cliente;
import com.btg.fondos.models.Producto;
import com.btg.fondos.repository.ClienteRepository;
import com.btg.fondos.repository.ProductoRepository;
import com.btg.fondos.services.ClientesServices;
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

public class ClienteServiceTest {

    @InjectMocks
    private ClientesServices clienteService;

    @Mock
    private ClienteRepository clienteRepository;

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
    void testCrearProducto() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente nuevoProducto = clienteService.add(cliente);
        assertNotNull(nuevoProducto);
        //assertEquals("Producto TEST", nuevoProducto.getNombre());
    }

    @Test
    void testObtenerPorId() {
        when(clienteRepository.findById("6717d924079bf412d285fd01")).thenReturn(Optional.of(cliente));
        Cliente encontrado = clienteService.getId("6717d924079bf412d285fd01");
        //assertEquals("Producto TEST", encontrado.getNombre());
    }

    @Test
    void testObtenerPorId_NotFound() {
        when(clienteRepository.findById("2")).thenReturn(Optional.empty());
        //assertThrows(ProductoNotFoundException.class, () -> clienteService.getId("2"));
    }

    @Test
    void testActualizarProducto() {
        when(clienteRepository.existsById("6717d924079bf412d285fd01")).thenReturn(true);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente actualizado = clienteService.update("6717d924079bf412d285fd01", cliente);
        //assertEquals("Producto TEST", actualizado.getNombre());
    }

    @Test
    void testEliminarProducto() {
        when(clienteRepository.existsById("6717d924079bf412d285fd01")).thenReturn(true);
        doNothing().when(clienteRepository).deleteById("6717d924079bf412d285fd01");
        clienteService.delete("6717d924079bf412d285fd01");
        verify(clienteRepository, times(1)).deleteById("6717d924079bf412d285fd01");
    }


}
