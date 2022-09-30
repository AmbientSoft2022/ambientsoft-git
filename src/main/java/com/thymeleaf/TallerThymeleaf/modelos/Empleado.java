package com.thymeleaf.TallerThymeleaf.modelos;


import com.thymeleaf.TallerThymeleaf.Enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empleado", nullable = false)
    private Long id_empleado;
    @Column(name="nombreEmpleado")
    private String nombreEmpleado;
    @Column(name="correo",unique = true)
    private String correo;
    @Column(name="rol")
    private Tipo rol;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="createdAt")
    private LocalDateTime createdAt;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="updatedAt")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "empleado")
    private Perfil perfil;
    @ManyToOne
    private Empresa empresa;
    @OneToMany(mappedBy = "empleado")
    private List<MovimientoDinero> movimientos;

}