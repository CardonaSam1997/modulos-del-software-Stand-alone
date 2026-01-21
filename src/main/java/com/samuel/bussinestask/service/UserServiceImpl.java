package com.samuel.bussinestask.service;
import com.samuel.bussinestask.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserServiceImpl {

    User guardarUsuario(User user);

    User actualizarUsuario(Integer id, User user);

    Optional<User> buscarUsuarioPorId(Integer id);

    List<User> obtenerTodosLosUsuarios();

    void eliminarUsuario(Integer id);

    boolean verificarEmailUsuario(String email);

    boolean verificarNombreUsuario(String userName);

    Optional<User> buscarUsuarioPorEmail(String email);

    Optional<User> buscarUsuarioPorNombre(String userName);

    List<User> buscarUsuariosPorNombre(String userName);

    List<User> buscarUsuariosPorEmail(String email);
}