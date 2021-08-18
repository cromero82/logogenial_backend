package com.rc.logogenial.basicadminservice.domain.dto;

import com.rc.logogenial.basicadminservice.entity.Role;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuarioDto {

    private int id;

    @NotNull(message="El campo es obligatorio")
    @Size(max = 80, message = "Longitud del campo máxima es de 12")
    private String username;

    @NotNull(message="El campo es obligatorio")
    @Size(max = 255, message = "Longitud del campo máxima es de 12")
    private String password;

    private Integer estado;

    @NotNull(message="El campo es obligatorio")
    @Size(max = 50, message = "Longitud del campo máxima es de 50")
    private String nombre;

    @NotNull(message="El campo es obligatorio")
    @Size(max = 50, message = "Longitud del campo máxima es de 50")
    private String apellido;

    private String avatar;

    @Size(min = 6, max = 255, message = "La longitud debe estar entre 6 y 255 ")
    private String email;

    private List<RoleDto> roles;

    @Builder.Default
    public Boolean activo = true;

    /**
     *
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    public UsuarioDto(int id, String username, String nombre, String apellido, String email, String avatar, List<Role> roles){
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.avatar = avatar;
        this.roles = roles.stream().map( rol -> new RoleDto( rol.getNombre() )).collect(Collectors.toList());
    }
}
