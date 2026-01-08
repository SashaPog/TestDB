package com.example.dbtest.entity;

import com.example.dbtest.enums.CompetitionLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
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
@Table(name = "competitions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"users", "tasks"})
@EqualsAndHashCode(exclude = {"users", "tasks"})
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private CompetitionLevel level;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "start_at", nullable = false)
    private ZonedDateTime startAt;

    @Column(name = "end_at")
    private ZonedDateTime endAt;

    @Builder.Default
    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_competitions",
        joinColumns = @JoinColumn(name = "competition_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();

    public void addUser (User user) {
        user.getCompetitions().add(this);
        users.add(user);
    }
}
