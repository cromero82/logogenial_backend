package com.rc.logogenial.basicadminservice.service;

import com.rc.logogenial.basicadminservice.domain.dto.UsuarioDto;
import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;

import java.util.List;

public interface IUsuarioService<T> extends IGenericService<T> {
    UsuarioDto getUserLogged();

    UsuarioDto findByUsername(String name);
    List<UsuarioDto> findAllStudends ();
    List<UsuarioDto> findAllTeachers ();
}
