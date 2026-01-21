package com.samuel.bussinestask.service;
import com.samuel.bussinestask.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserServiceImpl {

    /* =========================
       CRUD
       ========================= */

    User guardarUsuario(User user);

    User actualizarUsuario(Integer id, User user);

    Optional<User> buscarUsuarioPorId(Integer id);

    List<User> obtenerTodosLosUsuarios();

    void eliminarUsuario(Integer id);


    /* =========================
       VALIDACIONES
       ========================= */

    boolean verificarEmailUsuario(String email);

    boolean verificarNombreUsuario(String userName);


    /* =========================
       BÚSQUEDAS
       ========================= */

    Optional<User> buscarUsuarioPorEmail(String email);

    Optional<User> buscarUsuarioPorNombre(String userName);

    List<User> buscarUsuariosPorNombre(String userName);

    List<User> buscarUsuariosPorEmail(String email);
}