package com.tienda.ev1_dsy1103.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.ev1_dsy1103.Models.producto;
import com.tienda.ev1_dsy1103.Services.prooductoService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("api/productos")
public class productoController {
    
    private final prooductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable int id) {
        try {
            producto producto = productoService.getProductoById(id);
            if (producto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(producto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProducto(@PathVariable int id, @RequestBody producto producto) {
        try {
            producto updatedProducto = productoService.updateProducto(id, producto);
            if (updatedProducto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok("Producto actualizado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al actualizar el producto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto (@PathVariable int id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<producto>> findAll() {
        List<producto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<String> createProducto(@RequestBody producto producto) {
        try {
            productoService.saveProducto(producto);
            return ResponseEntity.status(201).body("Producto creado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error al crear el producto: " + e.getMessage());
        }
    }

}
