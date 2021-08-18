package com.rc.logogenial.basicadminservice.model.repository;

import com.rc.logogenial.basicadminservice.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {
    Page<Usuario> findAll(Pageable paging);

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByUsernameOrEmail(String username, String email);

    @Query(value = "select * from usuario u\n" +
            "inner join usuario_rol ur on u.id = ur.usuario_id\n" +
            "where ur.role_id = 3 and ur.usuario_id not in (select uri.usuario_id from usuario_rol uri where uri.role_id != 3)", nativeQuery = true)
    List<Usuario> findAllStudendts();

    @Query(value = "select * from usuario u\n" +
            "inner join usuario_rol ur on u.id = ur.usuario_id\n" +
            "where ur.role_id = 2 ", nativeQuery = true)
    List<Usuario> findAllTeachers();

    /*@Query("select u from usuarios u where u.username = ?1")
    public Usuario findByUsernameCustom(String username);*/
}