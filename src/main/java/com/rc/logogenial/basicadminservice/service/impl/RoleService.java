package com.rc.logogenial.basicadminservice.service.impl;

import com.rc.logogenial.basicadminservice.entity.Grupo;
import com.rc.logogenial.basicadminservice.entity.Role;
import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.model.repository.IGrupoRepository;
import com.rc.logogenial.basicadminservice.model.repository.IRolRepository;
import com.rc.logogenial.basicadminservice.model.shared.ResultSearchData;
import com.rc.logogenial.basicadminservice.service.IGenericSimpleService;
import com.rc.logogenial.basicadminservice.service.IGrupoService;
import com.rc.logogenial.basicadminservice.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseService<Role> implements IRoleService {

    @Autowired
    private IRolRepository repository;

    @Override
    public Role findByNombre(String nombre) throws ResourceNotFoundException {
        return  repository.findByNombre(nombre);
    }
}
