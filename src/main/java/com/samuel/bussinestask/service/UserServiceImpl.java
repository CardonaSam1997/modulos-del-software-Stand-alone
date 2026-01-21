package com.samuel.bussinestask.service;
import com.samuel.bussinestask.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserServiceImpl {

    User guardarUsuario(User User);

    Optional<User> buscarUsuarioPorId(int id);

    List<User> obtenerUsuario();

    List<User> obtenerUsuarios(Iterable<Integer> lista);

    void eliminarUsuario(int id);

    Optional<User> verificarEmailUsuario(String email);

    Optional<User> verificarUsuario(String user);
}
