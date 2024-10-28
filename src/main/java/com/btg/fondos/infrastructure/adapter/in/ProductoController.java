package com.btg.fondos.infrastructure.adapter.in;

import com.btg.fondos.application.services.ProductosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.btg.fondos.domain.models.Producto;
import java.util.List;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductosServices productosServices;

    // Obtener todos los products
    @GetMapping
    public List<Producto> obtenerProductos() {
        return productosServices.getAll();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable String id) {
        Producto producto = productosServices.getId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo producto
    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        Producto newProducto = productosServices.add(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProducto).getBody();
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable String id, @RequestBody Producto producto) {
        Producto productoUpdate = productosServices.update(id, producto);
        return ResponseEntity.ok(productoUpdate);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        productosServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
