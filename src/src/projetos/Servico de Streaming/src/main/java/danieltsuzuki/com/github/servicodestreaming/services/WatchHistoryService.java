package danieltsuzuki.com.github.servicodestreaming.services;

import danieltsuzuki.com.github.servicodestreaming.dtos.watchStory.WatchHistoryDto;
import danieltsuzuki.com.github.servicodestreaming.entities.Film;
import danieltsuzuki.com.github.servicodestreaming.entities.User;
import danieltsuzuki.com.github.servicodestreaming.entities.WatchHistory;
import danieltsuzuki.com.github.servicodestreaming.repositories.WatchHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class WatchHistoryService {
    private final WatchHistoryRepository watcHistoryRepository;

    public WatchHistoryService(WatchHistoryRepository watcHistoryRepository) {
        this.watcHistoryRepository = watcHistoryRepository;
    }

    public WatchHistoryDto saveHistoric(UUID userId, UUID filmId) {
        WatchHistory history = new WatchHistory(null, new User(userId), new Film(filmId), Instant.now());
        history = watcHistoryRepository.save(history);
        return new WatchHistoryDto(history);
    }

    public Page<WatchHistoryDto> getHistoricWatchedFilms(UUID userId, int page, int size) {
        return watcHistoryRepository.findAllByUserId(userId, PageRequest.of(page, size)).map(WatchHistoryDto::new);
    }
}
