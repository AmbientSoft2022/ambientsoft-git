package com.thymeleaf.TallerThymeleaf.controller;

import com.thymeleaf.TallerThymeleaf.modelos.Enterprise;
import com.thymeleaf.TallerThymeleaf.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EnterpriseController {

        @Autowired
        EnterpriseService empresaService;

        @GetMapping ({"/","/VerEmpresas"})
        public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje){
            List<Enterprise> listaEmpresas=empresaService.getAllEmpresas();
            model.addAttribute("emplist",listaEmpresas);
            model.addAttribute("mensaje",mensaje);
            return "verEmpresas"; //Llamamos al HTML
        }

        @GetMapping("/AgregarEmpresa")
        public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
            Enterprise emp= new Enterprise();
            model.addAttribute("emp",emp);
            model.addAttribute("mensaje",mensaje);
            return "agregarEmpresa";
        }

        @PostMapping("/GuardarEmpresa")
        public String guardarEmpresa(Enterprise emp, RedirectAttributes redirectAttributes){
            if(empresaService.saveOrUpdateEmpresa(emp)==true){
                redirectAttributes.addFlashAttribute("mensaje","saveOK");
                return "redirect:/VerEmpresas";
            }
            redirectAttributes.addFlashAttribute("mensaje","saveError");
            return "redirect:/AgregarEmpresa";
        }

        @GetMapping("/EditarEmpresa/{id}")
        public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
            Enterprise emp=empresaService.getEmpresaById(id);
            //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
            model.addAttribute("emp",emp);
            model.addAttribute("mensaje", mensaje);
            return "editarEmpresa";
        }


        @PostMapping("/ActualizarEmpresa")
        public String updateEmpresa(@ModelAttribute("emp") Enterprise emp, RedirectAttributes redirectAttributes){
            if(empresaService.saveOrUpdateEmpresa(emp)){
                redirectAttributes.addFlashAttribute("mensaje","updateOK");
                return "redirect:/VerEmpresas";
            }
            redirectAttributes.addFlashAttribute("mensaje","updateError");
            return "redirect:/EditarEmpresa";

        }

        @GetMapping("/EliminarEmpresa/{id}")
        public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
            if (empresaService.deleteEmpresa(id)==true){
                redirectAttributes.addFlashAttribute("mensaje","deleteOK");
                return "redirect:/VerEmpresas";
            }
            redirectAttributes.addFlashAttribute("mensaje", "deleteError");
            return "redirect:/VerEmpresas";
        }
}
