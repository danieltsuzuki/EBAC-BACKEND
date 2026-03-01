package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.dtos.film.FilmResponseDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.watchStory.WatchHistoryDto;
import danieltsuzuki.com.github.servicodestreaming.entities.Film;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.repositories.FilmRepository;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.ResourceNotFoundException;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.WatchFilmException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final WatchHistoryService watchHistoryService;

    public FilmService(FilmRepository filmRepository, WatchHistoryService watchHistoryService) {
        this.filmRepository = filmRepository;
        this.watchHistoryService = watchHistoryService;
    }

    public Page<FilmResponseDto> getFilmsFilteredPaged(User user, int page, int size) {
        return filmRepository.findAllByAgeRatingAndPlanType(user.getPerfil().getAgeLimit(), user.getAccount().getPlanType().ordinal(), PageRequest.of(page, size)).map(FilmResponseDto::new);
    }

    public WatchHistoryDto watchFilm(User user, UUID filmId) {
        try {
            Film film = filmRepository.findById(filmId).orElseThrow(
                    () -> new ResourceNotFoundException("Film not found")
            );

            if (user.getPerfil().getAgeLimit() < film.getAgeRating())
                throw new WatchFilmException("User is not allowed to watch this film due to age restrictions");

            Thread.sleep(film.getDuration() * 1000L);

            return watchHistoryService.saveHistoric(user.getId(), film.getId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WatchFilmException("Watching film was interrupted");
        }
    }

    public Page<WatchHistoryDto> getHistoricWatchedFilms(User user, int page, int size) {
        return watchHistoryService.getHistoricWatchedFilms(user.getId(), page, size);
    }
}
