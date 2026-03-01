package danieltsuzuki.com.github.servicodestreaming.repositories;

import danieltsuzuki.com.github.servicodestreaming.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE accounts SET plan_type = :planType WHERE id = :id")
    void updatePlanTypeById(UUID id, String planType);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM accounts WHERE id = :id and subscriber_user_id = :subscriberUserId")
    void deleteByIdAndSubscriberUserId(@Param("id") UUID id, @Param("subscriberUserId") UUID subscriberUserId);

}
