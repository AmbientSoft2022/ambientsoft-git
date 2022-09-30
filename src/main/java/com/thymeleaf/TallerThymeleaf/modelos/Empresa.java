package com.thymeleaf.TallerThymeleaf.modelos;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nit_Empresa", nullable = false)
    private Long nit_Empresa;
    @Column(name="nombreEmpresa",unique = true)
    private String nombreEmpresa;
    @Column(name="numeroDocumentoEmpresa",unique = true)
    private String documentoEmpresa;
    @Column(name="telefono")
    private long telefono;
    @Column(name="direccion")
    private String direccion;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="createdAt")
    private LocalDateTime createdAt;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="updatedAt")
    private LocalDateTime updatedAt;

//    @OneToMany(mappedBy = "empresa")
//    private List<Empleado> empleados;
//    @OneToMany(mappedBy = "empresa")
//    private List<MovimientoDinero> movimientos;
}
