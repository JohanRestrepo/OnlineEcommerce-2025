package com.ecommerce.spring_ecommerce.service;

import com.ecommerce.spring_ecommerce.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {
    public Optional<Usuario> findById(Integer id);

}
