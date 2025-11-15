package com.ecommerce.spring_ecommerce.controller;

import com.ecommerce.spring_ecommerce.model.Producto;
import com.ecommerce.spring_ecommerce.model.Usuario;
import com.ecommerce.spring_ecommerce.service.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String show(){
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto){
        System.out.println(producto);
        Usuario u = new Usuario(1,"","","","","","","");
        producto.setUsuario(u);
        productoService.save(producto);
        return "redirect:/productos";
    }
}
