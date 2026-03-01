package danieltsuzuki.com.github.servicodestreaming.dtos.film;

import danieltsuzuki.com.github.servicodestreaming.entities.Film;
import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;

import java.util.UUID;

public record FilmResponseDto (
         UUID id,
         String title,
         int ageRating,
         String genre,
         PlanTypeEnum planType,
         int duration
){
    public FilmResponseDto(Film film) {
        this(
                film.getId(),
                film.getTitle(),
                film.getAgeRating(),
                film.getGenre(),
                film.getPlanType(),
                film.getDuration()
        );
    }
}
