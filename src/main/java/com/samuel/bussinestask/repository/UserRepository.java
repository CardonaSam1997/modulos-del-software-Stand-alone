package com.samuel.bussinestask.repository;
import com.samuel.bussinestask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Busqueda personalizada
     * por email
     * por username
     * por id y estado
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String userName);

    Optional<User> findByIdAndEnableTrue(Integer id);

    /**
     * Validar existencia
     * por email
     * por username
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    /**
     * Valida que no exista otro usuario con el mismo email
     * y con un id diferente
     * @param email
     * @param id
     * @return
     */
    boolean existsByEmailAndIdNot(String email, Integer id);

    /**
     * Valida que no exista otro usuario con el mismo userName
     * y con un id diferente
     * @param userName
     * @param id
     * @return
     */
    boolean existsByUserNameAndIdNot(String userName, Integer id);

    /**
     *
     * @param email
     * @return
     */
    Optional<User> findByEmailAndEnableTrue(String email);

    Optional<User> findByUserNameAndEnableTrue(String userName);

    List<User> findByEnableTrue();

    List<User> findByAuthenticationTrue();

    List<User> findByCompletedFalse();

    /**
     * Buscar usuario por username o email con las letras 'x' filtradas
     * @param userName
     * @return
     */
    List<User> findByUserNameContainingIgnoreCase(String userName);

    List<User> findByEmailContainingIgnoreCase(String email);
}