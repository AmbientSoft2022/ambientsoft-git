package com.thymeleaf.TallerThymeleaf.controller;


import com.thymeleaf.TallerThymeleaf.modelos.Empresa;
import com.thymeleaf.TallerThymeleaf.service.GestorEmpresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FrontControllerEmpresa {

    @Autowired
    GestorEmpresa gestorEmpresa;

    @GetMapping("/Empresas")
    public String empresas(Model model){
        model.addAttribute("listaempresas",gestorEmpresa.consultaListaEmpresa());
        return "empresas";
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Empresa emp= new Empresa();
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje",mensaje);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if(gestorEmpresa.saveOrUpdateEmpresa(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/Empresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarEmpresa";
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Long id, @ModelAttribute("mensaje") String mensaje){
        Empresa emp=gestorEmpresa.getEmpresaById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(@ModelAttribute("emp") Empresa emp, RedirectAttributes redirectAttributes){
        if(gestorEmpresa.saveOrUpdateEmpresa(emp)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/Empresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarEmpresa";

    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if (gestorEmpresa.deleteEmpresa(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/Empresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/Empresas";
    }
}
