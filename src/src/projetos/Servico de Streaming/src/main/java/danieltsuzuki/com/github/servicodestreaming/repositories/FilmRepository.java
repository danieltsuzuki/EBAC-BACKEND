package danieltsuzuki.com.github.servicodestreaming.repositories;

import danieltsuzuki.com.github.servicodestreaming.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<Film, UUID> {
    Optional<Film> findById(UUID id);

    @Query(nativeQuery = true, value = "SELECT * FROM films WHERE age_rating < :ageRating AND plan_type <= :planType")
    Page<Film> findAllByAgeRatingAndPlanType(
            @Param("ageRating") int ageRating,
            @Param("planType") int planType,
            Pageable pageable
    );
}
