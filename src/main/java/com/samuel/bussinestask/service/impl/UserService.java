package com.samuel.bussinestask.service.impl;

import com.samuel.bussinestask.entity.Role;
import com.samuel.bussinestask.entity.User;
import com.samuel.bussinestask.exception.EmailDuplicadoException;
import com.samuel.bussinestask.exception.NombreUsuarioDuplicadoException;
import com.samuel.bussinestask.exception.UserNoEncontradoException;
import com.samuel.bussinestask.repository.UserRepository;
import com.samuel.bussinestask.service.UserServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImpl {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User guardarUsuario(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailDuplicadoException(user.getEmail());
        }

        if (userRepository.existsByUserName(user.getUserName())) {
            throw new NombreUsuarioDuplicadoException(user.getUserName());
        }

        user.setEnable(true);
        user.setAuthentication(false);
        user.setCompleted(false);
        user.setCreatedAt(new Date());

        // Rol por defecto
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        return userRepository.save(user);
    }

    @Override
    public User actualizarUsuario(Integer id, User user) {

        User usuarioExistente = userRepository.findById(id)
                .orElseThrow(() -> new UserNoEncontradoException(id));

        if (userRepository.existsByEmailAndIdNot(user.getEmail(), id)) {
            throw new EmailDuplicadoException(user.getEmail());
        }

        if (userRepository.existsByUserNameAndIdNot(user.getUserName(), id)) {
            throw new NombreUsuarioDuplicadoException(user.getUserName());
        }

        usuarioExistente.setUserName(user.getUserName());
        usuarioExistente.setEmail(user.getEmail());
        usuarioExistente.setPassword(user.getPassword());
        usuarioExistente.setCompleted(user.isCompleted());

        // Cambio de rol (solo si aplica)
        if (user.getRole() != null) {
            usuarioExistente.setRole(user.getRole());
        }

        usuarioExistente.setUpdatedAt(new Date());

        return userRepository.save(usuarioExistente);
    }

    @Override
    public Optional<User> buscarUsuarioPorId(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> obtenerTodosLosUsuarios() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> buscarUsuarioPorEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> buscarUsuarioPorNombre(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> buscarUsuariosPorNombre(String userName) {
        return userRepository.findByUserNameContainingIgnoreCase(userName);
    }

    @Override
    public List<User> buscarUsuariosPorEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    @Override
    public boolean verificarEmailUsuario(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean verificarNombreUsuario(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public void eliminarUsuario(Integer id) {

        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new UserNoEncontradoException(id));

        usuario.setEnable(false);
        usuario.setUpdatedAt(new Date());

        userRepository.save(usuario);
    }
}