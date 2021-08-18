package com.rc.logogenial.basicadminservice.service.impl;

import com.rc.logogenial.basicadminservice.entity.Grupo;
import com.rc.logogenial.basicadminservice.entity.Leccion;
import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.model.repository.IGrupoRepository;
import com.rc.logogenial.basicadminservice.model.shared.ResultSearchData;
import com.rc.logogenial.basicadminservice.service.IGenericService;
import com.rc.logogenial.basicadminservice.service.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService extends BaseService<Grupo> implements IGrupoService {

    @Autowired
    private IGrupoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Grupo create(Grupo grupo) {
        grupo.setUsuario(usuarioService.getUserEntityLogged());
        return repository.save(grupo);
    }

    @Override
    public void delete(Grupo tema) throws ResourceNotFoundException {
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
            throw new ResourceNotFoundException("Grupo", "id", Integer.toString(id));
        }
    }

    @Override
    public Iterable<Grupo> findAll() {
        List<Grupo> lista = (List<Grupo>) repository.findAll();
        return lista;
    }

    @Override
    public Grupo findById(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent())
        {
            return repository.findById(id).get();
        }
        else
        {
            throw new ResourceNotFoundException("Grupo", "id",  Integer.toString(id));
        }
    }

    @Override
    public Grupo update(Grupo grupo) throws ResourceNotFoundException {
        if (repository.findById(grupo.getId()).isPresent())
        {
            grupo.setUsuario(usuarioService.getUserEntityLogged());
            return repository.save(grupo);
        }
        throw new ResourceNotFoundException("Grupo", "id", Integer.toString(grupo.getId()));
    }

    @Override
    public Iterable<Grupo> findAllByEstudianteId(int id) {
        return  repository.findAllByEstudianteId(id);
    }

    @Override
    public ResultSearchData<Grupo> findAllSearch(int page, int size, String sortBy, String sortOrder) {
        Pageable paging = PageRequest.of(page, size, sortOrder.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Usuario usuario = usuarioService.getUserEntityLogged();
        Page<Grupo> pagedResult = repository.findAllByUsuario_Id(paging, usuario.getId());
        return (ResultSearchData<Grupo>) this.getResultSearch(pagedResult);
    }

    @Override
    public List<Grupo> findAllByUsuarioId(int usuarioId) {
        return repository.findAllByUsuario_Id(usuarioId);
    }

    @Override
    public Grupo findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

}
