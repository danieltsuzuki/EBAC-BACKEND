package danieltsuzuki.com.github.aprimorandoapi.repositories;

import danieltsuzuki.com.github.aprimorandoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByNameAndAge(String name, int age);
}
