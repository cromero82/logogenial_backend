package com.rc.logogenial.basicadminservice.service;


import com.rc.logogenial.basicadminservice.entity.Grupo;
import com.rc.logogenial.basicadminservice.entity.Leccion;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.model.shared.ResultSearchData;

import java.util.List;

public interface IGrupoService extends IGenericService<Grupo> {
    public void delete(Grupo nivel) throws ResourceNotFoundException;
    Iterable<Grupo> findAllByEstudianteId(int id);
    List<Grupo> findAllByUsuarioId(int id);

    Grupo findByNombre(String invitado);
}
