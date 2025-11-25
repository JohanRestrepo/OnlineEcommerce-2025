package com.ecommerce.spring_ecommerce.service;

import com.ecommerce.spring_ecommerce.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> findAll();
    public Optional<Usuario> findById(Integer id);
    public Usuario save(Usuario usuario);
    public Optional<Usuario> findByMail(String mail);

}
