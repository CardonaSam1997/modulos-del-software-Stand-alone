package com.samuel.bussinestask.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import com.samuel.bussinestask.dto.*;
import com.samuel.bussinestask.entity.User;
import com.samuel.bussinestask.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Crear el usuario
     * @param dto
     * @return
     */

    @PostMapping
    public ResponseEntity<UserResponseDTO> crearUsuario(@Valid @RequestBody UserCrearDTO dto) {

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        User guardado = userService.guardarUsuario(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponseDTO(guardado));
    }

    /**
     * Actualizar el usuario
     * @param id
     * @param dto
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER','COMPANY')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> actualizarUsuario(@PathVariable Integer id, @Valid @RequestBody UserActualizarDTO dto) {
        System.out.println("valores: "+id+"user: " + dto);
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setEnable(dto.isEnable());
        user.setCompleted(dto.isCompleted());

        User actualizado = userService.actualizarUsuario(id, user);
        return ResponseEntity.ok(toResponseDTO(actualizado));
    }

    /**
     * Obtener usuario por id
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER','COMPANY')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> obtenerUsuario(@PathVariable Integer id) {
        return userService.buscarUsuarioPorId(id)
                .map(user -> ResponseEntity.ok(toResponseDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtener todos los usuarios
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping()
    public List<UserResponseDTO> listarUsuarios() {
        return userService.obtenerTodosLosUsuarios()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }


    /**
     * Buscar usuarios por username o email
     * @param userName
     * @param email
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/buscar")
    public List<UserResponseDTO> buscar(@RequestParam(required = false) String userName, @RequestParam(required = false) String email) {

        List<User> usuarios;
        if (userName != null) {
            usuarios = userService.buscarUsuariosPorNombre(userName);
        } else if (email != null) {
            usuarios = userService.buscarUsuariosPorEmail(email);
        } else {
            usuarios = userService.obtenerTodosLosUsuarios();
        }

        return usuarios.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Eliminar usuario por id
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        userService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Mapea el user a userDTO
     * @param user
     * @return
     */
    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setEnable(user.isEnable());
        dto.setCompleted(user.isCompleted());
        return dto;
    }

}