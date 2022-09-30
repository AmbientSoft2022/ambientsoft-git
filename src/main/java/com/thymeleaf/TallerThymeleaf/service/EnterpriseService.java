package com.thymeleaf.TallerThymeleaf.service;

import com.thymeleaf.TallerThymeleaf.modelos.Enterprise;
import com.thymeleaf.TallerThymeleaf.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Le decimos a Spring que esta clase es de servicios
@Service
public class EnterpriseService {
    @Autowired //Conectamos esta clase con el repository de Empresa
    EnterpriseRepository empresaRepository; //Creamos un objeto tipo EmpresaRepository para poder usar los metodos que dicha clase hereda

    //Metodo que retornará la lista de empresas usando metodos heredados del jpaRepository
    public List<Enterprise> getAllEmpresas(){
        List<Enterprise> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa));  //Recorremos el iterable que regresa el metodo findAll del Jpa y lo guardamos en la lista creada
        return empresaList;
    }

    //Metodo que me trae un objeto de tipo Empresa cuando cuento con el id de la misma
    public Enterprise getEmpresaById(Integer id){
        return empresaRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar objetos de tipo Empresa
    public boolean saveOrUpdateEmpresa(Enterprise empresa){
        Enterprise emp=empresaRepository.save(empresa);
        if (empresaRepository.findById(emp.getId())!=null){
            return true;
        }
        return false;
    }

    //Metodo para eliminar empresas registradas teniendo el id
    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);  //Eliminar

        if (empresaRepository.findById(id)!=null){  //Verificacion del servicio eliminacion
            return true;
        }
        return false;
    }

}

