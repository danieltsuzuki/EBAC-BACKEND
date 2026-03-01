package danieltsuzuki.com.github.servicodestreaming.entities;

import danieltsuzuki.com.github.servicodestreaming.enums.PerfilEnum;
import danieltsuzuki.com.github.servicodestreaming.enums.UserTypeEnum;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = true)
    private Account account;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

    public User() {
    }

    public User (UUID id) {
        this.id = id;
    }

    public User(Account account, UserTypeEnum userType, String name, int age, String email, String password) {
        this.account = account;
        this.userType = userType;
        this.name = name;
        this.age = age;
        this.perfil = PerfilEnum.definePerfilByAge(age);
        this.email = email;
        this.password = password;
    }

    public User(Account account, UserTypeEnum userType, String name, int age, String email, String password, PerfilEnum perfil) {
        this.account = account;
        this.userType = userType;
        this.name = name;
        this.age = age;
        this.perfil = perfil;
        this.email = email;
        this.password = password;
    }

    public User(UUID id, Account account, UserTypeEnum userType, String name, int age, String email, String password, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.account = account;
        this.userType = userType;
        this.name = name;
        this.age = age;
        this.perfil = PerfilEnum.definePerfilByAge(age);
        this.email = email;
        this.password = password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userType.toString()));
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void updateUserType(UserTypeEnum newType) {
        this.userType = newType;
    }

}
