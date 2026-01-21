package com.samuel.bussinestask.repository;
import com.samuel.bussinestask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    /**
     * Busca un usuario por email
     * retorna el usuario encontrado
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);


    /**
     * Valida si existe un usuario por el username,
     * devuelve un valor boolean
     * @param userName
     * @return
     */
    boolean existsByUserName(String userName);
}
