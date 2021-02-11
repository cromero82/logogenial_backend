package com.rc.logogenial.basicadminservice.model.repository;

import com.rc.logogenial.basicadminservice.entity.Grupo;
import com.rc.logogenial.basicadminservice.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRolRepository extends CrudRepository<Role,Integer> {
    public Role findByNombre(@Param("nombre") String nombre);
}