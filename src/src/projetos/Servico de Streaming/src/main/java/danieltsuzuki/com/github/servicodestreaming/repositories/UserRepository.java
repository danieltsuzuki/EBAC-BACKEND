package danieltsuzuki.com.github.servicodestreaming.repositories;

import danieltsuzuki.com.github.servicodestreaming.entities.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @EntityGraph(attributePaths = {"account"})
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query(nativeQuery = true, value = """
    SELECT * FROM users
    WHERE account_id = (SELECT account_id FROM users WHERE id = :subscriberUserId)
    AND id != :subscriberUserId
    """)
    List<User> findAllUsersByAccountSubscriberUser(@Param("subscriberUserId") UUID subscriberUser);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM users WHERE account_id IN (SELECT account_id FROM users WHERE id = :subscriberUserId) AND id != :subscriberUserId")
    long countByUsersByAccountSubscriberUser(UUID subscriberUserId);

    @NotNull
    @EntityGraph(attributePaths = {"account"})
    Optional<User> findById(UUID id);

    void deleteById(UUID id);
}
