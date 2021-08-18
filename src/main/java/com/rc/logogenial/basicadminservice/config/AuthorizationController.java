package com.rc.logogenial.basicadminservice.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.rc.logogenial.basicadminservice.config.models.JwtResponse;
import com.rc.logogenial.basicadminservice.config.models.Login;
import com.rc.logogenial.basicadminservice.domain.dto.UsuarioDto;
import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.exception.ErrorPersistException;
import com.rc.logogenial.basicadminservice.exception.ResourceNotFoundException;
import com.rc.logogenial.basicadminservice.exception.UnauthorizedRequestException;
import com.rc.logogenial.basicadminservice.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthorizationController.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    /** The ldap properties. */
//    @Value("${spring.ldap.properties}")
//    private String ldapProperties;

    /** The authentication manager. */
    @Autowired
    AuthenticationManager authenticationManager;

    /** The user repository. */
    @Autowired
    UsuarioService usuarioService;

    /** The lista item repository. */
//    @Autowired
//    RepositoryListaItem listaItemRepository;

    /** The jwt provider. */
    @Autowired
    JwtProvider jwtProvider;

    /** The ldap authentication manager. */
//    @Autowired
//    UmvLdapAuthenticationProvider ldapAuthenticationManager;


    /**
     * Authenticate.
     *
     * @param loginRequest the login request
     * @return the response entity
     * @throws UnauthorizedRequestException the unauthorized request exception
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody Login loginRequest) throws UnauthorizedRequestException {
        Authentication authentication = null;
        int contador = 0;
        if (loginRequest.getUsername() != null) {
            Usuario user = usuarioService.findEntityByUsername(loginRequest.getUsername());
            if (user != null) {
                try {
                    if (user.getEstado()== 1 || user.getEstado() == 0) {
                        String clave = passwordEncoder.encode(loginRequest.getPassword());
                        UsernamePasswordAuthenticationToken authentication1 = new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(), loginRequest.getPassword());
                        authentication = authenticationManager.authenticate(authentication1);

                        if (authentication.isAuthenticated()) {
                            int exitososPrevios = user.getIntentosExitosos() ;
                            contador = exitososPrevios + 1;
                            user.setIntentosExitosos(contador);
                        } else {
                           int fallidosPrevios = user.getIntentosFallidos();
                            contador = fallidosPrevios + 1;
                            user.setIntentosFallidos(contador);
                            throw new UnauthorizedRequestException("Usuario o clave incorrectos.");
                        }
                        usuarioService.updateIntentos(user);
                    } else {
                        throw new UnauthorizedRequestException("El usuario no esta activo en el sistema.");
                    }
                }
                // Si hubo un error, aumenta el numero de intentos fallidos.
                catch (Exception e) {
                    contador = user.getIntentosFallidos() + 1;
                    user.setIntentosFallidos(contador);
                    try {
                        usuarioService.updateIntentos(user);
                    } catch ( ResourceNotFoundException resourceNotFoundException) {
                        throw new ErrorPersistException("Error actualizando datos fallidos de usuario");
                    }
                }
            } else {
                throw new UnauthorizedRequestException("Usuario o clave incorrectos.");
            }
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    /**
     * Unauthorized request handler.
     *
     * @param e the e
     * @return the list
     */
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ExceptionHandler(UnauthorizedRequestException.class)
//    public List<FieldErrorMessage> unauthorizedRequestHandler(UnauthorizedRequestException e) {
//        FieldErrorMessage error = new FieldErrorMessage();
//        error.setMessage(e.getMessage());
//        List<FieldErrorMessage> fieldErrors = new ArrayList<FieldErrorMessage>();
//        fieldErrors.add(error);
//        return fieldErrors;
//    }

}