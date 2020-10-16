package com.rc.logogenial.basicadminservice.controller;

import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ResourceFoundException;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.model.shared.ResultSearchData;
import com.rc.logogenial.basicadminservice.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({ "v1/usuario-api" })
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private IGenericService<Usuario> usuarioService;

    @GetMapping(value = "/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) throws ResourceFoundException, ResourceNotFoundException {
        return new ResponseEntity<>(usuarioService.create(usuario), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<Usuario> findOne(@RequestParam int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> update(@RequestBody Usuario Usuario) throws ResourceNotFoundException {
        return new ResponseEntity<>(usuarioService.update(Usuario), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> delete(@RequestParam int id) throws ResourceNotFoundException {
        usuarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<Usuario>> findAll() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResultSearchData<Usuario>> search(@RequestParam(name = "page", defaultValue = "0") int page,
                                                            @RequestParam(name = "size", defaultValue = "10") int size, @RequestParam(name = "sortBy") String sortBy
            , @RequestParam(name = "sortOrder") String sortOrder ) {
        ResultSearchData<Usuario> datos = usuarioService.findAllSearch(page, size,sortBy, sortOrder);
        return new ResponseEntity<ResultSearchData<Usuario>>(datos, new HttpHeaders(), HttpStatus.OK);
    }
}
