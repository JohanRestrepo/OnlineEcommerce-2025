package com.ecommerce.spring_ecommerce.controller;

import com.ecommerce.spring_ecommerce.model.Orden;
import com.ecommerce.spring_ecommerce.model.Usuario;
import com.ecommerce.spring_ecommerce.service.IOrdenService;
import com.ecommerce.spring_ecommerce.service.IUsuarioService;
import com.ecommerce.spring_ecommerce.service.UsuarioServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

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
    public String acceder(Usuario usuario, HttpSession session){
        Optional<Usuario> user = usuarioService.findByMail(usuario.getMail());
        if(user.isPresent()){
            session.setAttribute("idusuario", user.get().getId());
            if(user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else{
                return "redirect:/";
            }
        }else{
            System.out.println("usuario no existe");
        }
        return "redirect:/";
    }

    @GetMapping("/compras")
    public String obtenerCompras(HttpSession session, Model model){
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);

        model.addAttribute("ordenes", ordenes);
        return "usuario/compras";
    }
}
