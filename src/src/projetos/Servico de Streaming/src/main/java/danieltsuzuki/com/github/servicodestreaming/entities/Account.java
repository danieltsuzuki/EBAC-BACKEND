package danieltsuzuki.com.github.servicodestreaming.entities;

import danieltsuzuki.com.github.servicodestreaming.enums.PlanTypeEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @Column(nullable = false, updatable = false, unique = true)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type")
    private PlanTypeEnum planType;

    @OneToOne
    @JoinColumn(name = "subscriber_user_id", nullable = true)
    private User subscriberUser;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public Account() {
    }

    public Account(PlanTypeEnum planType) {
        this.planType = planType;
    }

    public Account(UUID id, PlanTypeEnum planType, User subscriberUser, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.planType = planType;
        this.subscriberUser = subscriberUser;
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

    public PlanTypeEnum getPlanType() {
        return planType;
    }

    public User getSubscriberUser() {
        return subscriberUser;
    }


    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void updateSubscriberUser(User subscriberUser) {
        this.subscriberUser = subscriberUser;
    }

}