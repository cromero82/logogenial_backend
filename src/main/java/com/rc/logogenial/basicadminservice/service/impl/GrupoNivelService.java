package com.rc.logogenial.basicadminservice.service.impl;

import com.rc.logogenial.basicadminservice.entity.Grupo;
import com.rc.logogenial.basicadminservice.entity.GrupoNivel;
import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.model.repository.IGrupoNivelRepository;
import com.rc.logogenial.basicadminservice.model.shared.PageablePrimitive;
import com.rc.logogenial.basicadminservice.model.shared.ResultSearchData;
import com.rc.logogenial.basicadminservice.service.IGrupoNivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GrupoNivelService extends BaseService<GrupoNivel> implements IGrupoNivelService {

    @Autowired
    private IGrupoNivelRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public GrupoNivel create(GrupoNivel grupo) {
        return repository.save(grupo);
    }

    @Override
    public void delete(GrupoNivel tema) throws ResourceNotFoundException {
        if (repository.findById(tema.getId()).isPresent()) {
            repository.delete(tema);
        }
        throw new ResourceNotFoundException("nema", "id", Integer.toString(tema.getId()));
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
    public Iterable<GrupoNivel> findAll() {
        return  repository.findAll();
    }

    @Override
    public GrupoNivel findById(int id) throws ResourceNotFoundException {
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
    public GrupoNivel update(GrupoNivel nivel) throws ResourceNotFoundException {
        if (repository.findById(nivel.getId()).isPresent())
        {
            return repository.save(nivel);
        }
        throw new ResourceNotFoundException("Niveles", "id", Integer.toString(nivel.getId()));
    }

    @Override
    public Iterable<GrupoNivel> findAllByGrupoId(int id) {
        return  repository.findAllByGrupo_Id(id);
    }

    @Override
    public ResultSearchData<GrupoNivel> Search(PageablePrimitive pag, int grupoId) {

        Pageable paging = PageRequest.of(pag.getPage(), pag.getSize(),
                pag.getSortBy().equals("asc") ? Sort.by(pag.getSortOrder()).ascending() : Sort.by(pag.getSortBy()).descending());
        Usuario usuario = usuarioService.getUserEntityLogged();
        Page<GrupoNivel> pagedResult = repository.findAllByGrupo_Id(paging, grupoId);
        return (ResultSearchData<GrupoNivel>) this.getResultSearch(pagedResult);
    }
}
