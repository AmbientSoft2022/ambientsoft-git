package com.thymeleaf.TallerThymeleaf.controller;

import com.thymeleaf.TallerThymeleaf.modelos.Empleado;
import com.thymeleaf.TallerThymeleaf.service.GestorEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FrontControllerEmpleado {

    @Autowired
    GestorEmpleado gestorEmpleado;

    @GetMapping("/Empleados")
    public String empleados(Model model){
        model.addAttribute("listaempleados",gestorEmpleado.consultaListaEmpleados());
        return "empleados";
    }

    @GetMapping("/AgregarEmpleado")
    public String nuevoEmpleado(Model model, @ModelAttribute("mensaje") String mensaje){
        Empleado emp= new Empleado();
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje",mensaje);
        return "agregarEmpleado";
    }

    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Empleado emp, RedirectAttributes redirectAttributes){
        if(gestorEmpleado.saveOrUpdateEmpleado(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/Empleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarEmpleado";
    }

    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Long id, @ModelAttribute("mensaje") String mensaje){
        Empleado emp=gestorEmpleado.getEmpleadoById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpleado";
    }

    @PostMapping("/ActualizarEmpleado")
    public String updateEmpleado(@ModelAttribute("emp") Empleado emp, RedirectAttributes redirectAttributes){
        if(gestorEmpleado.saveOrUpdateEmpleado(emp)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/Empleados";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarEmpleado";

    }

    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if (gestorEmpleado.deleteEmpleado(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/Empleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/Empleados";
    }


}
