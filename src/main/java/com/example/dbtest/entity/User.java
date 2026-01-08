package com.example.dbtest.entity;

import com.example.dbtest.enums.Role;
import com.example.dbtest.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"news", "competitions", "tasks"})
@EqualsAndHashCode(exclude = {"news", "competitions", "tasks"})
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true, columnDefinition = "varchar(60)")
    private String uuid;

    @Column( name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Builder.Default
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    private List<News> news = new ArrayList<>();

    @Builder.Default
    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Competition> competitions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    public void addCompetition (Competition competition) {
        competition.getUsers().add(this);
        competitions.add(competition);
    }
}
