package com.samuel.bussinestask.service.impl;

import com.samuel.bussinestask.entity.User;
import com.samuel.bussinestask.repository.UserRepository;
import com.samuel.bussinestask.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImpl {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User guardarUsuario(User User) {
        return null;
    }

    @Override
    public Optional<User> buscarUsuarioPorId(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> obtenerUsuario() {
        return List.of();
    }

    @Override
    public List<User> obtenerUsuarios(Iterable<Integer> lista) {
        return List.of();
    }

    @Override
    public void eliminarUsuario(int id) {

    }

    @Override
    public Optional<User> verificarEmailUsuario(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> verificarUsuario(String user) {
        return Optional.empty();
    }
}
