package com.thymeleaf.TallerThymeleaf.service;


import com.thymeleaf.TallerThymeleaf.Enums.TipoMonto;
import com.thymeleaf.TallerThymeleaf.modelos.Empleado;
import com.thymeleaf.TallerThymeleaf.modelos.Empresa;
import com.thymeleaf.TallerThymeleaf.modelos.MovimientoDinero;
import com.thymeleaf.TallerThymeleaf.repository.EmpleadoRepositorio;
import com.thymeleaf.TallerThymeleaf.repository.EmpresaRepositorio;
import com.thymeleaf.TallerThymeleaf.repository.MovimientoDineroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestorMovimiento {

    @Autowired
    MovimientoDineroRepositorio gestorMovimientoRepositorio;

    @Autowired
    EmpresaRepositorio empresaRepositorio;
    @Autowired
    EmpleadoRepositorio empleadoRepositorio;
    @Autowired
    GestorEmpresa gestorEmpresa;

    public List<MovimientoDinero> consultaListaMovimientoDinero() {
        return gestorMovimientoRepositorio.findAll();
    }


    public List<MovimientoDinero> crearMovimiento(Long nit_Empresa, MovimientoDinero MD) {
            Optional<Empresa> empresa = empresaRepositorio.findById(nit_Empresa);
            if(empresa != null && !empresa.isEmpty()){
            MD.setNit(nit_Empresa);
            gestorMovimientoRepositorio.save(MD);
            }
        return gestorMovimientoRepositorio.findAll();
    }

    public List<Empresa> consultaListas(){
        return gestorEmpresa.empresaRepositorio.findAll();
    }

    public List<MovimientoDinero> consultaMovimientoEmpresa(Long nit){
        return gestorMovimientoRepositorio.findByNit(nit);
    }

    public MovimientoDinero actualizarNombreMovimiento(Long nit, MovimientoDinero Movimiento){
        MovimientoDinero MD = gestorMovimientoRepositorio.findById(Movimiento.getId_MovimientoDinero()).get();
        MD.setConceptoMovimiento(Movimiento.getConceptoMovimiento());
        gestorMovimientoRepositorio.save(MD);
        return MD;
    }

    int cont_Monto=0;
    int cont_TM=0;
    int cont_CM=0;
    int cont_Nit=0;
    int cont_Empresa=0;
    int cont_Empleado=0;

    public MovimientoDinero actualizarMovimiento(Long nit, MovimientoDinero GM){
        MovimientoDinero MD = gestorMovimientoRepositorio.findById(GM.getId_MovimientoDinero()).get();


        if (cont_Monto==0 || GM.getMontoMovimiento()!=0){
            MD.setMontoMovimiento(GM.getMontoMovimiento());
            if (GM.getMontoMovimiento()!=0){
                cont_Monto=1;
            }
        }

        if (cont_TM==0 || GM.getTipoMonto()!=null){
            MD.setTipoMonto(GM.getTipoMonto());
            if (GM.getTipoMonto()!=null){
                cont_TM=1;
            }
        }

        if (cont_CM==0 || GM.getConceptoMovimiento()!=null){
            MD.setConceptoMovimiento(GM.getConceptoMovimiento());
            if (GM.getConceptoMovimiento()!=null){
                cont_CM=1;
            }
        }

        if (cont_Nit==0 || GM.getNit()!=0){
            MD.setNit(GM.getNit());
            if (GM.getNit()!=0){
                cont_Nit=1;
            }
        }

        if (cont_Empresa==0 || GM.getEmpresa()!=null){
            MD.setEmpresa(GM.getEmpresa());
            if (GM.getEmpresa()!=null){
                cont_Empresa=1;
            }
        }

        if (cont_Empleado==0 || GM.getEmpleado()!=null){
            MD.setEmpleado(GM.getEmpleado());
            if (GM.getEmpleado()!=null){
                cont_Empleado=1;
            }
        }

        gestorMovimientoRepositorio.save(MD);
        return MD;
    }
//    public boolean saveOrUpdateMovimiento(MovimientoDinero movimiento){
//        MovimientoDinero emp=gestorMovimientoRepositorio.save(movimiento);
//        if (gestorMovimientoRepositorio.findById(movimiento.getId_MovimientoDinero())!=null){
//            return true;
//        }
//        return false;
//    }

    public boolean saveOrUpdateMovimiento(MovimientoDinero movimiento){
        Optional<Empresa> comprobante_Empresa= empresaRepositorio.findById(movimiento.getEmpresa().getNit_Empresa());
        Optional<Empleado> comprobante_Empleado= empleadoRepositorio.findById(movimiento.getEmpleado().getId_empleado());
        System.out.println(movimiento.getTipoMonto().getClass().getSimpleName());
        if (
                (  (comprobante_Empresa!=null && !comprobante_Empresa.isEmpty())
                        &&
                        (comprobante_Empleado!=null && !comprobante_Empleado.isEmpty())  )
                        &&
                        (movimiento.getTipoMonto().equals(TipoMonto.gasto) || movimiento.getTipoMonto().equals(TipoMonto.ingreso))
        )
        {
            MovimientoDinero emp=gestorMovimientoRepositorio.save(movimiento);
            return true;
        }
        return false;
    }

    public MovimientoDinero getMovimientoById(Long id){
        return gestorMovimientoRepositorio.findById(id).get();
    }

    public boolean deleteMovimiento(Long id){
        gestorMovimientoRepositorio.deleteById(id);  //Eliminar

        if (gestorMovimientoRepositorio.findById(id)!=null){  //Verificacion del servicio eliminacion
            return true;
        }
        return false;
    }

    public List<MovimientoDinero> eliminarMovimiento(Long id){
        gestorMovimientoRepositorio.deleteById(id);
        return gestorMovimientoRepositorio.findAll();

    }

}
