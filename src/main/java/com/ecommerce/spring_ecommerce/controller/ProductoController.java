package com.ecommerce.spring_ecommerce.controller;

import com.ecommerce.spring_ecommerce.model.Producto;
import com.ecommerce.spring_ecommerce.model.Usuario;
import com.ecommerce.spring_ecommerce.service.IProductoService;
import com.ecommerce.spring_ecommerce.service.IUsuarioService;
import com.ecommerce.spring_ecommerce.service.UploadFileService;
import com.ecommerce.spring_ecommerce.service.UsuarioServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private UploadFileService upload;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos",productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {

        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        producto.setUsuario(u);
        //imagen
        if(producto.getId()==null){//cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto=productoService.get(id);
        producto = optionalProducto.get();
        System.out.println("edit: Producto encontrado: " + producto);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {

        Producto p = new Producto();
        p=productoService.get(producto.getId()).get();

        if(file.isEmpty()){//editamos un producto pero no cambiamos la imagen
            producto.setImagen(p.getImagen());
        }else{//cuando se edita tambien la imagen
            //eliminar la imagen siempre y cuando no sea la de por defecto
            if(!p.getImagen().equals("default.jpg")){
                upload.deleteImage(p.getImagen());
            }
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Producto p = new Producto();
        p = productoService.get(id).get();
        //eliminar la imagen siempre y cuando no sea la de por defecto
        if(!p.getImagen().equals("default.jpg")){
            upload.deleteImage(p.getImagen());
        }

        productoService.delete(id);
        return "redirect:/productos";
    }
}
