package com.tienda.ev1_dsy1103.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.ev1_dsy1103.Models.producto;
import com.tienda.ev1_dsy1103.Services.prooductoService;


@RestController
@RequestMapping("api/productos")
public class productoController {
    
    private prooductoService productoService;


    public ResponseEntity<producto> getProductoById (int id) {
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

    public ResponseEntity<producto> saveProducto (producto producto) {
        try {
            producto savedProducto = productoService.saveProducto(producto);
            return ResponseEntity.ok(savedProducto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<producto> updateProducto (int id, producto producto) {
        try {
            producto updatedProducto = productoService.updateProducto(id, producto);
            if (updatedProducto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedProducto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Void> deleteProducto (int id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    public ResponseEntity<String> findAll() {
        return ResponseEntity.ok("Lista de productos");
    }
}
