package co.com.luis.salas.GestorUsuarios.repository;

import co.com.luis.salas.GestorUsuarios.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCedula(int cedula);
    boolean existsByCedula(int cedula);

}
