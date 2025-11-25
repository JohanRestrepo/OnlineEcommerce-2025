package com.ecommerce.spring_ecommerce.service;

import com.ecommerce.spring_ecommerce.model.Orden;
import com.ecommerce.spring_ecommerce.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IOrdenService {
    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);

}
