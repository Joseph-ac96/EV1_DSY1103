package com.tienda.ev1_dsy1103.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class producto {

    @EqualsAndHashCode.Include
    private int id;

    private String nombre;

    private int precio;

    private int stock;

    private String categoria;
    
}
