package danieltsuzuki.com.github.servicodestreaming.controllers;

import danieltsuzuki.com.github.servicodestreaming.configs.OpenApiConfig;
import danieltsuzuki.com.github.servicodestreaming.dtos.film.FilmResponseDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.watchStory.WatchHistoryDto;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.services.FilmService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    @PreAuthorize("hasRole('SUBSCRIBER') or hasRole('LINKED')")
    public ResponseEntity<Page<FilmResponseDto>> getFilms(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @AuthenticationPrincipal User currentUser) {
        var films = filmService.getFilmsFilteredPaged(currentUser, page, size);
        return ResponseEntity.ok(films);
    }

    @PostMapping("/{filmId}/watch")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER') or hasRole('ROLE_LINKED')")
    public ResponseEntity<WatchHistoryDto> watchFilm(@PathVariable String filmId, @AuthenticationPrincipal User currentUser) {
        WatchHistoryDto watchHistory = filmService.watchFilm(currentUser, UUID.fromString(filmId));
        return ResponseEntity.created(URI.create("/films/watch-history")).body(watchHistory);
    }

    @GetMapping("/watch-history")
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER') or hasRole('ROLE_LINKED')")
    public ResponseEntity<Page<WatchHistoryDto>> getHistoricWatchedFilms(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @AuthenticationPrincipal User currentUser) {
        Page<WatchHistoryDto> historic = filmService.getHistoricWatchedFilms(currentUser, 0, 10);
        return ResponseEntity.ok(historic);
     }

}
