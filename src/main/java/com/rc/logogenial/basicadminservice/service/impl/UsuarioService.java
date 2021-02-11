package com.rc.logogenial.basicadminservice.service.impl;

import com.rc.logogenial.basicadminservice.entity.*;
import com.rc.logogenial.basicadminservice.exception.ResourceFoundException;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;

import com.rc.logogenial.basicadminservice.model.dto.UsuarioDto;
import com.rc.logogenial.basicadminservice.model.shared.ResultSearchData;
import com.rc.logogenial.basicadminservice.model.repository.UsuarioRepository;
import com.rc.logogenial.basicadminservice.service.IGenericService;
import com.rc.logogenial.basicadminservice.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Service
@Transactional
public class UsuarioService extends  BaseService<Usuario> implements IUsuarioService<Usuario, UsuarioDto>,  UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private GrupoEstudianteService grupoEstudianteService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private RoleService roleService;

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario getUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return repository.findByUsername(userDetails.getUsername());
    }

    @Override
    public UsuarioDto createSecure(UsuarioDto usuarioDto) throws ResourceFoundException, ResourceNotFoundException {
        Usuario usuario = convertDtoToEntity(usuarioDto); //repository.findByUsernameOrEmail(usuario.getUsername(), usuario.getEmail());
        String clave = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(clave);
        usuario.setUsername(usuarioDto.getEmail());
        usuario.setIntentosExitosos(0L);
        usuario.setIntentosFallidos(0L);
        Usuario nuevoUsuario = repository.save(usuario);
        return  convertEntityToDto(nuevoUsuario);
    }

    private UsuarioDto convertEntityToDto(Usuario entity) {
        UsuarioDto dto = new UsuarioDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setEstado(entity.getActivo().equals(true) ? 1: 0);
        dto.setActivo(entity.getActivo());
        dto.setEmail(entity.getEmail());
        dto.setAvatar(entity.getAvatar());
        for(Role rolEntity: entity.getRoles()){
            dto.getRoles().add(rolEntity.getNombre());
        }
        return dto;
    }

    private Usuario convertDtoToEntity(UsuarioDto dto) {
        Usuario entity = null;
        if(dto.getId()> 0){
            entity = findByUsername(dto.getUsername());
        }else {
            entity = new Usuario();
        }
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setEstado(dto.getActivo().equals(true) ? 1: 0);
        entity.setActivo(dto.getActivo());
        entity.setEmail(dto.getEmail());
        entity.setAvatar(dto.getAvatar());
        entity.setPassword(dto.getPassword());
        for(String rolDto: dto.getRoles()){
            Role rol = new Role();
            rol.setNombre(rolDto);
            switch (rolDto){
                case "ADMINISTRADOR":
                    rol.setId(1L);
                    break;
                case "TUTOR":
                    rol.setId(2L);
                    break;
                case "ESTUDIANTE":
                    rol.setId(3L);
                    break;
            }
            entity.getRoles().add(rol);
        }
        return entity;
    }

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username);

        if(usuario == null) {
            logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(),  usuario.getActivo() == true ? true: false, true, true, true, authorities);
    }

    @Transactional(readOnly=true)
    public Usuario findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Usuario create(Usuario usuario) throws ResourceFoundException, ResourceNotFoundException {
        Optional<Usuario> usuarioConsulta = repository.findByUsernameOrEmail(usuario.getUsername(), usuario.getEmail());
        if(usuarioConsulta.isPresent()) {
            throw new ResourceFoundException("Usuario "+ usuario.getUsername() + " o email: " + usuario.getEmail());
        }
        String clave = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(clave);
        usuario.setIntentosExitosos(0L);
        usuario.setIntentosFallidos(0L);

        if(usuario.getRoles().size() > 0) {
            if(usuario.getRoles().get(0).getId() == 0){
                Role rolRegistrado = roleService.findByNombre(usuario.getRoles().get(0).getNombre());
                if(rolRegistrado != null){
                    usuario.getRoles().set(0, rolRegistrado);
                }
                usuario.setEstado(0);
            }
        }
        Usuario nuevoUsuario = repository.save(usuario);
        return  nuevoUsuario;
    }

    public void delete(Usuario Usuario) throws ResourceNotFoundException {
        if (repository.findById(Usuario.getId()).isPresent())
        {
            repository.delete(Usuario);
        }
        throw new ResourceNotFoundException("User", "id",  Integer.toString(Usuario.getId()));
    }

    public void deleteById(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent())
        {
            repository.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException("User", "id", Integer.toString(id));
        }
    }

    public Iterable<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent())
        {
            return repository.findById(id).get();
        }
        else
        {
            throw new ResourceNotFoundException("User", "id",  Integer.toString(id));
        }
    }

    public Usuario update(Usuario usuario) throws ResourceNotFoundException {
        Optional<Usuario> usuarioConsultado = repository.findById(usuario.getId());
        if (usuarioConsultado.isPresent())
        {
            usuarioConsultado.get().setEmail(usuario.getEmail());
            usuarioConsultado.get().setUsername(usuario.getUsername());
            usuarioConsultado.get().setNombre(usuario.getNombre());
            String clave = usuario.getPassword();
            if(clave.length() <= 10)  {
                clave = passwordEncoder.encode(usuario.getPassword());
            }
            usuarioConsultado.get().setPassword(clave);
            if(usuario.getAvatar() == null && usuarioConsultado.get().getAvatar() == null) {
                usuarioConsultado.get().setAvatar("av-1.png");
            }
            return repository.save(usuarioConsultado.get());
        }
        throw new ResourceNotFoundException("User", "id", Integer.toString(usuario.getId()));
    }

    @Override
    public ResultSearchData<Usuario> findAllSearch(int page, int size, String sortBy, String sortOrder) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Usuario> pagedResult = repository.findAll(paging);
        return (ResultSearchData<Usuario>) this.getResultSearch(pagedResult);
    }

    @Override
    public List<Usuario> findAllStudends() {
        return repository.findAllStudendts();
    }

    @Override
    public List<Usuario> findAllTeachers() {
        return repository.findAllTeachers();
    }
}
