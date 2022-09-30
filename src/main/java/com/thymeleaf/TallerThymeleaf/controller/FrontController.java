package com.thymeleaf.TallerThymeleaf.controller;


import com.thymeleaf.TallerThymeleaf.modelos.MovimientoDinero;
import com.thymeleaf.TallerThymeleaf.service.GestorMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FrontController {

    @Autowired
    GestorMovimiento gestorMovimiento;

//    @Autowired
//    GestorUsuario gestorUsuario;

//    @GetMapping("/")
//    public String index(Model model, @AuthenticationPrincipal OidcUser principal){
//        if (principal != null) {
//            Usuarios Usuario = this.gestorUsuario.obtenerOcrearUsuario(principal.getClaims());
//            model.addAttribute("Usuario",Usuario);
//            System.out.println(principal.getClaims());
//        }
//        return "index";
//    }

    @GetMapping("/MovimientosDinero")
    public String movimientodinero(Model model){
        model.addAttribute("listamovimientos",gestorMovimiento.consultaListaMovimientoDinero());
        return "movimientodinero";
    }

    @GetMapping("/AgregarMovimiento")
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero emp= new MovimientoDinero();
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje",mensaje);
        return "agregarMovimiento";
    }


    @GetMapping("/tasks")
    public String tasks(){
        return "tasks";
    }


    @PostMapping("/GuardarMovimiento")
    public String guardarMovimiento(MovimientoDinero emp, RedirectAttributes redirectAttributes){
        if(gestorMovimiento.saveOrUpdateMovimiento(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/MovimientosDinero";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimiento(Model model, @PathVariable Long id, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero emp=gestorMovimiento.getMovimientoById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje", mensaje);
        return "editarMovimiento";
    }

    @PostMapping("/ActualizarMovimiento")
    public String updateMovimiento(@ModelAttribute("emp") MovimientoDinero emp, RedirectAttributes redirectAttributes){
        if(gestorMovimiento.saveOrUpdateMovimiento(emp)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/MovimientosDinero";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarMovimiento";

    }

    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if (gestorMovimiento.deleteMovimiento(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/MovimientosDinero";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/MovimientosDinero";
    }
}
