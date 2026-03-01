package danieltsuzuki.com.github.servicodestreaming.dtos.watchStory;

import danieltsuzuki.com.github.servicodestreaming.entities.WatchHistory;

import java.time.Instant;
import java.util.UUID;

public record WatchHistoryDto(
        UUID id,
        UUID userId,
        UUID filmId,
        Instant watchedAt
) {
    public WatchHistoryDto(WatchHistory watchHistory) {
        this(
                watchHistory.getId(),
                watchHistory.getUser().getId(),
                watchHistory.getFilm().getId(),
                watchHistory.getWatchedAt());
    }
}
