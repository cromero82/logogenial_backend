package com.rc.logogenial.basicadminservice.config;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.rc.logogenial.basicadminservice.config.models.UserPrincipal;
import com.rc.logogenial.basicadminservice.domain.dto.UsuarioDto;
import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.service.impl.UsuarioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioDetailService.
 */
@Service
public class UsuarioDetailService implements UserDetailsService {

    /** The user repository. */
    @Autowired
    UsuarioService usuarioService;

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @SneakyThrows
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioDto user = usuarioService.findByUsername(username);

        if (user == null ) {
            throw new ResourceNotFoundException("User Not Found with -> username or email : " + username);
        }
        List<GrantedAuthority> authorities = user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());

        return new UserPrincipal(
                (long) user.getId(),
                user.getNombre() + " " + user.getApellido(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}