package com.rc.logogenial.basicadminservice.service;

import com.rc.logogenial.basicadminservice.entity.Role;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;

public interface IRoleService {
    Role findByNombre(String nombre) throws ResourceNotFoundException;
}
