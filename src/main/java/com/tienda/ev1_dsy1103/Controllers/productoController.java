package com.tienda.ev1_dsy1103.Controllers;

import org.apache.catalina.connector.Response;
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
