package com.rc.logogenial.basicadminservice.config;

import com.rc.logogenial.basicadminservice.entity.Usuario;
import com.rc.logogenial.basicadminservice.service.impl.UsuarioService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtProvider.
 */
@Component
public class JwtProvider {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    /** The jwt secret. */
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    /** The jwt expiration. */
    @Value("${app.jwt.expiration}")
    private int jwtExpiration;

    /** The user repository. */
    @Autowired
    UsuarioService userRepository;

    /**
     * Generate jwt token.
     *
     * @param authentication the authentication
     * @return the string
     */
    public String generateJwtToken(Authentication authentication) {

        Usuario user = userRepository.findEntityByUsername(authentication.getName());

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with -> username or email : " + authentication.getName());
        }

        return Jwts.builder()
                .setSubject((user.getUsername()))
                .claim("nombre",user.getNombre())
                .claim("apellido",user.getApellido())
                .claim("email", user.getEmail())
                .claim("id", user.getId())
                .claim("avatar", user.getAvatar())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Validate jwt token.
     *
     * @param authToken the auth token
     * @return true, if successful
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    /**
     * Gets the user name from jwt token.
     *
     * @param token the token
     * @return the user name from jwt token
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
}