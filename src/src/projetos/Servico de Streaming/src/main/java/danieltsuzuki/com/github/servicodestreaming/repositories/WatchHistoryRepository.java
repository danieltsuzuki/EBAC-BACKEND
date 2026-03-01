package danieltsuzuki.com.github.servicodestreaming.repositories;

import danieltsuzuki.com.github.servicodestreaming.entities.WatchHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, UUID> {
    Page<WatchHistory> findAllByUserId(UUID userId, Pageable pageable);

    @Modifying
    @Query(value = "DELETE FROM watch_history  WHERE user_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") UUID userId);
}
