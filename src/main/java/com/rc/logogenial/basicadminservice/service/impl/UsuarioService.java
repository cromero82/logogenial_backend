package com.rc.logogenial.basicadminservice.service.impl;

import com.rc.logogenial.basicadminservice.domain.dto.UsuarioDto;
import com.rc.logogenial.basicadminservice.entity.Grupo;
import com.rc.logogenial.basicadminservice.entity.GrupoEstudiante;
import com.rc.logogenial.basicadminservice.entity.Role;
import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ResourceFoundException;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService extends  BaseService<Usuario> implements IUsuarioService<UsuarioDto>,  UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private GrupoEstudianteService grupoEstudianteService;

    @Autowired
    private GrupoService grupoService;

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDto getUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Usuario ent = repository.findByUsername(userDetails.getUsername()).get();
        return  new UsuarioDto(ent.getId(), ent.getUsername(), ent.getNombre(), ent.getApellido(), ent.getEmail(), ent.getAvatar(), ent.getRoles());
    }

    public Usuario getUserEntityLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Usuario ent = repository.findByUsername(userDetails.getUsername()).get();
        return  ent;
    }

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username).get();

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
    public UsuarioDto findByUsername(String username) {
        Usuario ent = repository.findByUsername(username).get();
        return  new UsuarioDto(ent.getId(), ent.getUsername(), ent.getNombre(), ent.getApellido(), ent.getEmail(), ent.getAvatar(), ent.getRoles());
    }

    public Usuario findEntityByUsername(String username) {
        Usuario ent = repository.findByUsername(username).get();
        return  ent;
    }

    public UsuarioDto create(UsuarioDto usuarioDto) throws ResourceFoundException, ResourceNotFoundException {
        Optional<Usuario> usuarioConsulta = repository.findByUsernameOrEmail(usuarioDto.getUsername(), usuarioDto.getEmail());
        if(usuarioConsulta.isPresent()) {
            throw new ResourceFoundException("Usuario "+ usuarioDto.getUsername() + " o email: " + usuarioDto.getEmail());
        }
        Usuario usuario = new Usuario();
        String clave = passwordEncoder.encode(usuarioDto.getPassword());
        usuario.setUsername( usuarioDto.getUsername());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido( usuarioDto.getApellido());
        usuario.setEmail( usuarioDto.getEmail());
        usuario.setPassword(clave);
        usuario.setIntentosExitosos(0);
        usuario.setIntentosFallidos(0);

        // Por defecto el usuario esta inactivo
        usuario.setEstado(0);


        Role rol = new Role();

        // usuarios de la app Mobile no traen roles por ende son estudiantes
        if(usuarioDto.getRoles().size() ==0) {
            rol.setId(3L);
            rol.setNombre("ESTUDIANTE");
            usuario.setEstado(1);
        }else {
            // Configura el rol que solicitó
            switch(usuarioDto.getRoles().get(0).getNombre().toLowerCase(Locale.ROOT)){
                case "docente / tutor":
                    rol.setId(2L);
                    rol.setNombre("TUTOR");
                    break;
                case "administrador":
                    rol.setId(1L);
                    rol.setNombre("ADMINISTRADOR");
                    break;
                case "estudiante":
                    rol.setId(1L);
                    rol.setNombre("ADMINISTRADOR");
                    usuario.setEstado(1);
                    break;
            }
        }
        List<Role> roles  = new ArrayList<>();
        roles.add(rol);
        usuario.setRoles(roles);

        Usuario nuevoUsuario = repository.save(usuario);
        if(usuarioDto.getRoles().size() == 1){
            // para usuarios estudiantes
            GrupoEstudiante grupoEstudiante = new GrupoEstudiante();
            Grupo grupoInvitado = grupoService.findByNombre("Invitado");
            grupoEstudiante.setGrupo(grupoInvitado);
            grupoEstudiante.setUsuarioestudiante(nuevoUsuario);
            grupoEstudianteService.create(grupoEstudiante);
        }
        usuarioDto.setId(usuario.getId());
        return  usuarioDto;
    }

    public void delete(UsuarioDto usuarioDto) throws ResourceNotFoundException {
        Optional<Usuario> usuario = repository.findById(usuarioDto.getId());
        if (repository.findById(usuarioDto.getId()).isPresent())
        {
            repository.delete(usuario.get());
        }else{
            throw new ResourceNotFoundException("User", "id",  Integer.toString(usuarioDto.getId()));
        }
    }

    public void deleteById(int id) throws ResourceNotFoundException {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent())
        {
            repository.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException("User", "id", Integer.toString(id));
        }
    }

    public Iterable<UsuarioDto> findAll() {
//        Iterable<Usuario> estudiantes = repository.findAll();
//        List<UsuarioDto> estuddiantesDto = estudiantes.iterator().map(ent ->
//                new UsuarioDto(ent.getUsername(), ent.getNombre(), ent.getApellido(), ent.getEmail(), ent.getAvatar(), ent.getRoles()))
//                .collect(Collectors.toList());
//        return  estuddiantesDto;
//        return repository.findAll();
        return null;
    }

    public UsuarioDto findById(int id) throws ResourceNotFoundException {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent())
        {
            Usuario ent = usuario.get();
            return  new UsuarioDto(ent.getId(), ent.getUsername(), ent.getNombre(), ent.getApellido(), ent.getEmail(), ent.getAvatar(), ent.getRoles());
        }
        else
        {
            throw new ResourceNotFoundException("User", "id",  Integer.toString(id));
        }
    }

    @Override
    public UsuarioDto update(UsuarioDto usuarioDto) throws ResourceNotFoundException {
        Optional<Usuario> usuarioConsultado = repository.findByUsername(usuarioDto.getUsername());
        if (usuarioConsultado.isPresent())
        {
            usuarioConsultado.get().setEmail(usuarioDto.getEmail());
            usuarioConsultado.get().setUsername(usuarioDto.getUsername());
            usuarioConsultado.get().setNombre(usuarioDto.getNombre());
            String clave = usuarioDto.getPassword();
            if(clave.length() <= 10)  {
                clave = passwordEncoder.encode(usuarioDto.getPassword());
            }
            usuarioConsultado.get().setPassword(clave);
            if(usuarioDto.getAvatar() == null && usuarioConsultado.get().getAvatar() == null) {
                usuarioConsultado.get().setAvatar("av-1.png");
            }
            repository.save(usuarioConsultado.get());
            return  usuarioDto;
        }
        throw new ResourceNotFoundException("User", "id", Integer.toString(usuarioDto.getId()));
    }

    @Override
    public ResultSearchData<UsuarioDto> findAllSearch(int page, int size, String sortBy, String sortOrder) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Usuario> pagedResult = repository.findAll(paging);
        ResultSearchData<Usuario> resultSearch = this.getResultSearch(pagedResult);
        ResultSearchData<UsuarioDto> result = new ResultSearchData<>();
        result.setSize( resultSearch.getSize());
        result.setTotalElements( resultSearch.getTotalElements());
        result.setTotalPages( resultSearch.getTotalPages());
        result.setContent( resultSearch.getContent().stream().map( ent ->
            new UsuarioDto( ent.getId(), ent.getUsername(), ent.getNombre(), ent.getApellido(), ent.getEmail(), ent.getAvatar(), ent.getRoles() ) )
                .collect(Collectors.toList()) );
        return  result;
    }

    @Override
    public List<UsuarioDto> findAllStudends() {
        List<Usuario> estudiantes = repository.findAllStudendts();
        List<UsuarioDto> estuddiantesDto = estudiantes.stream().map(ent ->
                new UsuarioDto(ent.getId(), ent.getUsername(), ent.getNombre(), ent.getApellido(), ent.getEmail(), ent.getAvatar(), ent.getRoles()))
                .collect(Collectors.toList());
        return  estuddiantesDto;
    }

    @Override
    public List<UsuarioDto> findAllTeachers() {
        List<Usuario> profesores = repository.findAllTeachers();
        List<UsuarioDto> profesoresDto = profesores.stream().map(ent ->
                new UsuarioDto(ent.getId(), ent.getUsername(), ent.getNombre(), ent.getApellido(), ent.getEmail(), ent.getAvatar(), ent.getRoles()))
                .collect(Collectors.toList());
        return  profesoresDto;
    }

    public void updateIntentos(Usuario usuario) throws ResourceNotFoundException {
        repository.save(usuario);
    }
}
