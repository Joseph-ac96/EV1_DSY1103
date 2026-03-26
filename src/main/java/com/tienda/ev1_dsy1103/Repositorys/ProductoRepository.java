package com.tienda.ev1_dsy1103.Repositorys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tienda.ev1_dsy1103.Models.producto;

@Repository
public class ProductoRepository {

    private final List<producto> productos = new ArrayList<>();

    public ProductoRepository () {
        productos.add(new producto(1, "Coca-cola", 20, 100, "Bebida"));
        productos.add(new producto(2, "Pepsi", 18, 50, "Bebida"));
        productos.add(new producto(3, "Fanta", 15, 30, "Bebida"));
        productos.add(new producto(4, "Sprite", 17, 20, "Bebida"));
        productos.add(new producto(5, "Agua mineral", 10, 200, "Bebida"));
    }

    public List<producto> findAll() {
        return productos;
    }

    public producto findById (int id) {
        return productos.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElse(null);
    }

    public void delete (int ind) {
        productos.removeIf(p -> p.getId() == ind);
    }
    

    public producto save (producto producto) {
        productos.add(producto);
        return producto;
    }

    public producto update (int id, producto producto) {
        producto existingProducto = findById(id);
        if (existingProducto != null) {
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setStock(producto.getStock());
            existingProducto.setCategoria(producto.getCategoria());
        }
        return existingProducto;
    }

}
