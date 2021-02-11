package com.rc.logogenial.basicadminservice.controller;

import com.rc.logogenial.basicadminservice.entity.Archivo;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.service.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping({ "v1/archivo-api" })
@CrossOrigin(origins= {"*"})
public class ArchivoController  {
    @Autowired
    private IArchivoService<Archivo, MultipartFile> service;

    @GetMapping(value = "/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(
            //@RequestBody Archivo data,
            @RequestParam("archivo") MultipartFile archivo)
            throws IOException
    {
        return new ResponseEntity<>(service.create( archivo), HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<Archivo> findById(@RequestParam int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Resource> verFoto(@PathVariable("id") int id) throws IOException {
        Resource recurso =  service.cargar(id);

        // para que se pueda descargar
        HttpHeaders cabecera = new HttpHeaders();
        if(recurso != null){
            cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ recurso.getFilename() +"\"");
        }
        File file = recurso.getFile();
        return ResponseEntity.ok().headers(cabecera).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }
}

