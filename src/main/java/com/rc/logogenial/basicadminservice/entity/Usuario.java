package com.rc.logogenial.basicadminservice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(name = "usuario_uk", columnNames = "username"))
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 80)
    private String username;

    @Column(length = 255)
    private String password;

    private Integer estado;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String apellido;

    @Column(length = 20)
    private String avatar;

    /** The Intentos fallidos. */
    @Column(name = "INTENTOS_FALLIDOS")
    private int intentosFallidos;

    /** The Intentos exitosos. */
    @Column(name = "INTENTOS_EXITOSOS")
    private int intentosExitosos;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name="usuario_rol", joinColumns= @JoinColumn(name="usuario_id"),
            inverseJoinColumns=@JoinColumn(name="role_id"),
            uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id", "role_id"})})
    private List<Role> roles;

    @Column(name = "activo", columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE")
    @Builder.Default
    public Boolean activo = true;

    @Column(name = "eliminado")
    public Date eliminado;

    /**
     *
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;
}
