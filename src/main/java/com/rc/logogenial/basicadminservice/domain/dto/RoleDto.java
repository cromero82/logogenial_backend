package com.rc.logogenial.basicadminservice.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    String nombre;
    public RoleDto( ) {

    }
    public RoleDto(String nombre) {
        this.setNombre(nombre);
    }
}
