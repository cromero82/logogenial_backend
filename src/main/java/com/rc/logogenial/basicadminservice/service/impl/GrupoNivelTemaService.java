package com.rc.logogenial.basicadminservice.service.impl;

import com.rc.logogenial.basicadminservice.entity.GrupoNivel;
import com.rc.logogenial.basicadminservice.entity.GrupoNivelTema;
import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.model.repository.IGrupoNivelRepository;
import com.rc.logogenial.basicadminservice.model.repository.IGrupoNivelTemaRepository;
import com.rc.logogenial.basicadminservice.model.shared.PageablePrimitive;
import com.rc.logogenial.basicadminservice.model.shared.ResultSearchData;
import com.rc.logogenial.basicadminservice.service.IGrupoNivelTemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GrupoNivelTemaService extends BaseService<GrupoNivelTema> implements IGrupoNivelTemaService {

    @Autowired
    private IGrupoNivelTemaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public GrupoNivelTema create(GrupoNivelTema grupoNivelTema) {
        return repository.save(grupoNivelTema);
    }

    @Override
    public void delete(GrupoNivelTema grupoNivelTema) throws ResourceNotFoundException {
        if (repository.findById(grupoNivelTema.getId()).isPresent()) {
            repository.delete(grupoNivelTema);
        }
        throw new ResourceNotFoundException("nema", "id", Integer.toString(grupoNivelTema.getId()));
    }

    @Override
    public void deleteById(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent())
        {
            repository.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException("Niveles", "id", Integer.toString(id));
        }
    }

    @Override
    public Iterable<GrupoNivelTema> findAll() {
        return  repository.findAll();
    }

    @Override
    public GrupoNivelTema findById(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent())
        {
            return repository.findById(id).get();
        }
        else
        {
            throw new ResourceNotFoundException("Niveles", "id",  Integer.toString(id));
        }
    }

    @Override
    public GrupoNivelTema update(GrupoNivelTema grupoNivelTema) throws ResourceNotFoundException {
        if (repository.findById(grupoNivelTema.getId()).isPresent())
        {
            return repository.save(grupoNivelTema);
        }
        throw new ResourceNotFoundException("Niveles", "id", Integer.toString(grupoNivelTema.getId()));
    }


    @Override
    public Iterable<GrupoNivelTema> findAllByGrupoNivelId(int id) {
        return  repository.findAllByGrupoNivel_Id(id);
    }

    @Override
    public ResultSearchData<GrupoNivelTema> Search(PageablePrimitive pag, int grupoNivelId) {

        Pageable paging = PageRequest.of(pag.getPage(), pag.getSize(),
                pag.getSortBy().equals("asc") ? Sort.by(pag.getSortOrder()).ascending() : Sort.by(pag.getSortBy()).descending());
        Usuario usuario = usuarioService.getUserEntityLogged();
        Page<GrupoNivelTema> pagedResult = repository.findAllByGrupoNivel_Id(paging, grupoNivelId);
        return (ResultSearchData<GrupoNivelTema>) this.getResultSearch(pagedResult);
    }
}
