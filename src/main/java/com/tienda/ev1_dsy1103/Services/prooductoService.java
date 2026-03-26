package com.tienda.ev1_dsy1103.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tienda.ev1_dsy1103.Models.producto;
import com.tienda.ev1_dsy1103.Repositorys.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class prooductoService {

    private final ProductoRepository productoRepository;

    public producto getProductoById (int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor que cero.");
        }
        return productoRepository.findById(id);
    }

    public producto saveProducto(producto producto) {
        try {
            validarProducto(producto);
            return productoRepository.save(producto);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al guardar el producto: " + e.getMessage());
        }
    }

    public boolean validarProducto (producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (producto.getCategoria() == null || producto.getCategoria().isBlank()) {
            throw new IllegalArgumentException("La categoría del producto no puede estar vacía.");
        }
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock del producto no puede ser negativo.");
        }
        if (producto.getId() <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor que cero.");
        }
        if (productoExiste(producto)) {
            throw new IllegalArgumentException("El producto con ID " + producto.getId() + " ya existe.");
        }
        return true;
    }

    public boolean productoExiste (producto producto) {
        return productoRepository.findAll().stream()
        .anyMatch(p -> p.getId() == producto.getId());
    }

    public producto updateProducto (int id, producto producto) {
        try {
            validarProducto(producto);
            return productoRepository.update(id, producto);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error al actualizar el producto: " + e.getMessage());
        }
    }

    public producto deleteProducto (int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser mayor que cero.");
        }
        producto producto = productoRepository.findById(id);
        if (producto == null) {
            throw new IllegalArgumentException("El producto con ID " + id + " no existe.");
        }
        productoRepository.delete(id);
        return producto;
    }

    public List<producto> obtenerTodos() {
        return productoRepository.findAll();
    }

}
