package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.dtos.film.FilmResponseDto;
import danieltsuzuki.com.github.servicodestreaming.dtos.watchStory.WatchHistoryDto;
import danieltsuzuki.com.github.servicodestreaming.entities.Account;
import danieltsuzuki.com.github.servicodestreaming.entities.Film;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.enums.PerfilEnum;
import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.enums.UserTypeEnum;
import danieltsuzuki.com.github.servicodestreaming.repositories.FilmRepository;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.ResourceNotFoundException;
import danieltsuzuki.com.github.servicodestreaming.services.Exceptions.WatchFilmException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {

    @Mock
    FilmRepository filmRepository;

    @Mock
    WatchHistoryService watchHistoryService;

    @InjectMocks
    FilmService filmService;

    UUID userId;
    UUID filmId;
    User user;
    Film film;
    Account account;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        filmId = UUID.randomUUID();
        account = new Account(PlanTypeEnum.BASIC);
        user = new User(userId, account, UserTypeEnum.SUBSCRIBER, "Test User", 25, "test@mail.com", "password", null, null);
        film = new Film(filmId, "Test Film", 14, "qualquer", PlanTypeEnum.BASIC, 1, null, null);
    }

    // =========================================================
    // getFilmsFilteredPaged
    // =========================================================
    @Nested
    class GetFilmsFilteredPaged {

        @Test
        void shouldReturnPagedFilms_WhenFilmsExist() {
            Page<Film> filmPage = new PageImpl<>(List.of(film));
            PageRequest pageRequest = PageRequest.of(0, 10);

            when(filmRepository.findAllByAgeRatingAndPlanType(
                    user.getPerfil().getAgeLimit(),
                    user.getAccount().getPlanType().ordinal(),
                    pageRequest
            )).thenReturn(filmPage);

            Page<FilmResponseDto> result = filmService.getFilmsFilteredPaged(user, 0, 10);

            assertNotNull(result);
            assertEquals(1, result.getTotalElements());
            verify(filmRepository, times(1)).findAllByAgeRatingAndPlanType(
                    user.getPerfil().getAgeLimit(),
                    user.getAccount().getPlanType().ordinal(),
                    pageRequest
            );
        }

        @Test
        void shouldReturnEmptyPage_WhenNoFilmsExist() {
            Page<Film> emptyPage = new PageImpl<>(List.of());
            PageRequest pageRequest = PageRequest.of(0, 10);

            when(filmRepository.findAllByAgeRatingAndPlanType(
                    user.getPerfil().getAgeLimit(),
                    user.getAccount().getPlanType().ordinal(),
                    pageRequest
            )).thenReturn(emptyPage);

            Page<FilmResponseDto> result = filmService.getFilmsFilteredPaged(user, 0, 10);

            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    // =========================================================
    // watchFilm
    // =========================================================
    @Nested
    class WatchFilm {

        @Test
        void shouldReturnWatchHistoryDto_WhenFilmIsWatchedSuccessfully() {
            WatchHistoryDto watchHistoryDto = mock(WatchHistoryDto.class);

            when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
            when(watchHistoryService.saveHistoric(userId, filmId)).thenReturn(watchHistoryDto);

            WatchHistoryDto result = filmService.watchFilm(user, filmId);

            assertNotNull(result);
            verify(filmRepository, times(1)).findById(filmId);
            verify(watchHistoryService, times(1)).saveHistoric(userId, filmId);
        }

        @Test
        void shouldThrowResourceNotFoundException_WhenFilmNotFound() {
            when(filmRepository.findById(filmId)).thenReturn(Optional.empty());

            ResourceNotFoundException exception = assertThrows(
                    ResourceNotFoundException.class,
                    () -> filmService.watchFilm(user, filmId)
            );

            assertEquals("Film not found", exception.getMessage());
            verify(filmRepository, times(1)).findById(filmId);
            verifyNoInteractions(watchHistoryService);
        }

        @Test
        void shouldThrowWatchFilmException_WhenUserIsTooYoungForFilm() {
            // Usuário com 10 anos tentando assistir filme com classificação 14
            User youngUser = new User(UUID.randomUUID(), account, UserTypeEnum.LINKED, "Young User", 10, "young@mail.com", "password", null, null);

            when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));

            WatchFilmException exception = assertThrows(
                    WatchFilmException.class,
                    () -> filmService.watchFilm(youngUser, filmId)
            );

            assertEquals("User is not allowed to watch this film due to age restrictions", exception.getMessage());
            verifyNoInteractions(watchHistoryService);
        }

        @Test
        void shouldThrowWatchFilmException_WhenThreadIsInterrupted() throws InterruptedException {
            when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));

            // Interrompe a thread antes de chamar o método
            Thread.currentThread().interrupt();

            WatchFilmException exception = assertThrows(
                    WatchFilmException.class,
                    () -> filmService.watchFilm(user, filmId)
            );

            assertEquals("Watching film was interrupted", exception.getMessage());
            // Limpa o estado de interrupção para não afetar outros testes
            Thread.interrupted();
        }
    }

    // =========================================================
    // getHistoricWatchedFilms
    // =========================================================
    @Nested
    class GetHistoricWatchedFilms {

        @Test
        void shouldReturnPagedWatchHistory_WhenHistoryExists() {
            WatchHistoryDto watchHistoryDto = mock(WatchHistoryDto.class);
            Page<WatchHistoryDto> historyPage = new PageImpl<>(List.of(watchHistoryDto));

            when(watchHistoryService.getHistoricWatchedFilms(userId, 0, 10)).thenReturn(historyPage);

            Page<WatchHistoryDto> result = filmService.getHistoricWatchedFilms(user, 0, 10);

            assertNotNull(result);
            assertEquals(1, result.getTotalElements());
            verify(watchHistoryService, times(1)).getHistoricWatchedFilms(userId, 0, 10);
        }

        @Test
        void shouldReturnEmptyPage_WhenNoHistoryExists() {
            Page<WatchHistoryDto> emptyPage = new PageImpl<>(List.of());

            when(watchHistoryService.getHistoricWatchedFilms(userId, 0, 10)).thenReturn(emptyPage);

            Page<WatchHistoryDto> result = filmService.getHistoricWatchedFilms(user, 0, 10);

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(watchHistoryService, times(1)).getHistoricWatchedFilms(userId, 0, 10);
        }
    }
}