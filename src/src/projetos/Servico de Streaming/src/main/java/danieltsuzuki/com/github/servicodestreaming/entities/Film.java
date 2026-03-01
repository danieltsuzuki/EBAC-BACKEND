package danieltsuzuki.com.github.servicodestreaming.entities;

import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private int ageRating;
    @Column(nullable = false, length = 50)
    private String genre;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private PlanTypeEnum planType;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public Film() {}
    public Film(UUID id) {
        this.id = id;
    }

    public Film(UUID id, String title, int ageRating, String genre, PlanTypeEnum planType, int duration, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.ageRating = ageRating;
        this.genre = genre;
        this.planType = planType;
        this.duration = duration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAgeRating() {
        return ageRating;
    }

    public String getGenre() {
        return genre;
    }

    public PlanTypeEnum getPlanType() {
        return planType;
    }

    public int getDuration() {
        return duration;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return ageRating == film.ageRating && duration == film.duration && Objects.equals(id, film.id) && Objects.equals(title, film.title) && Objects.equals(genre, film.genre) && planType == film.planType && Objects.equals(createdAt, film.createdAt) && Objects.equals(updatedAt, film.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, ageRating, genre, planType, duration, createdAt, updatedAt);
    }
}
