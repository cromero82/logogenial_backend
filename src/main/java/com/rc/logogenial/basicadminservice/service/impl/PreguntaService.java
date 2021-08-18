package com.rc.logogenial.basicadminservice.service.impl;

import com.rc.logogenial.basicadminservice.entity.Pregunta;
import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.model.repository.IPreguntaRepository;
import com.rc.logogenial.basicadminservice.service.IPreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreguntaService extends BaseService<Pregunta> implements IPreguntaService {

    @Autowired
    private IPreguntaRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Pregunta create(Pregunta pregunta) {
        Usuario usuario = usuarioService.getUserEntityLogged();
        pregunta.setUsuarioautor(usuario);
        return repository.save(pregunta);
    }

    @Override
    public void delete(Pregunta Pregunta) throws ResourceNotFoundException {
        if (repository.findById(Pregunta.getId()).isPresent()) {
            repository.delete(Pregunta);
        }
        throw new ResourceNotFoundException("nema", "id", Integer.toString(Pregunta.getId()));
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
    public Iterable<Pregunta> findAll() {
        return  repository.findAll();
    }

    @Override
    public Pregunta findById(int id) throws ResourceNotFoundException {
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
    public Pregunta update(Pregunta pregunta) throws ResourceNotFoundException {
        Optional<Pregunta> preguntaExistente = repository.findById(pregunta.getId());
        if (preguntaExistente.isPresent())
        {
            pregunta.setUsuarioautor(preguntaExistente.get().getUsuarioautor());
            pregunta.setAprobacion(preguntaExistente.get().getAprobacion());
            return repository.save(pregunta);
        }
        throw new ResourceNotFoundException("Niveles", "id", Integer.toString(pregunta.getId()));
    }

    @Override
    public Iterable<Pregunta> findAllByLeccion_Id(int id) {
        return  repository.findAllByLeccion_Id(id);
    }



//    @Override
//    public ResultSearchData<Pregunta> Search(PageablePrimitive pag, int grupoNivelId) {
//
//        Pageable paging = PageRequest.of(pag.getPage(), pag.getSize(),
//                pag.getSortBy().equals("asc") ? Sort.by(pag.getSortOrder()).ascending() : Sort.by(pag.getSortBy()).descending());
//        Usuario usuario = usuarioService.getUserLogged();
//        Page<Pregunta> pagedResult = repository.findAllByGrupoNivel_Id(paging, grupoNivelId);
//        return (ResultSearchData<Pregunta>) this.getResultSearch(pagedResult);
//    }
}
