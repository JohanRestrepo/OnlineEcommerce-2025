package com.ecommerce.spring_ecommerce.controller;

import com.ecommerce.spring_ecommerce.model.Usuario;
import com.ecommerce.spring_ecommerce.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/registro")
    public String registro(){
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){
        System.out.println("Usuario registro" + usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario){
        System.out.println("Usuario aceediendo: " + usuario);
        return "redirect:/";
    }
}
