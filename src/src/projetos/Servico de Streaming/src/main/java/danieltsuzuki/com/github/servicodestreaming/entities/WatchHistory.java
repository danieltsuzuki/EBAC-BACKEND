package danieltsuzuki.com.github.servicodestreaming.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "watch_history")
public class WatchHistory {
    @UuidGenerator
    @Id
    @Column(updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private User user;
    @JoinColumn(name = "film_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Film film;

    @Column(nullable = false, updatable = false)
    private Instant watchedAt;

    public WatchHistory() {
    }
    public WatchHistory(UUID id, User user, Film film, Instant watchedAt) {
        this.id = id;
        this.user = user;
        this.film = film;
        this.watchedAt = watchedAt;
    }

    @PrePersist
    protected void onCreate() {
        watchedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Film getFilm() {
        return film;
    }

    public Instant getWatchedAt() {
        return watchedAt;
    }
}
