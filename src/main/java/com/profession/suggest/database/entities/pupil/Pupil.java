package com.profession.suggest.database.entities.pupil;

import com.profession.suggest.database.entities.gender.Gender;
import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.dataanalys.psychtests.PsychTest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pupil")
@Getter
@Setter
@ToString(exclude = {"account", "gender"})
@EqualsAndHashCode(exclude = {"account", "gender"})
@NoArgsConstructor
@AllArgsConstructor
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Size(max = 50, message = "Patronymic must not exceed 50 characters")
    @Column(name = "patronymic", length = 50, nullable = false)
    private String patronymic;

    @NotNull(message = "Birthday is required")
    @Past(message = "Birthday must be in the past")
    @Column(name = "birthday")
    private LocalDate birthday;

    @NotBlank(message = "School is required")
    @Size(max = 200, message = "School name must not exceed 200 characters")
    @Column(name = "school", length = 200)
    private String school;

    @Size(max = 500, message = "Health condition must not exceed 500 characters")
    @Column(name = "health_condition", columnDefinition = "TEXT")
    private String healthCondition;

    @Size(max = 50, message = "Nationality must not exceed 50 characters")
    @Column(name = "nationality", length = 50)
    private String nationality;
    @Size(max = 1000, message = "Extra activities must not exceed 1000 characters")
    @Column(name = "extra_activities", columnDefinition = "TEXT")
    private String extraActivities;
    @Column(name = "class_number")
    private Integer classNumber;
    @Column(name = "class_label")
    private String classLabel;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", unique = true)
    private Account account;
    //OneToMany List PsychTests
    @OneToMany(mappedBy = "pupil", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PsychTest> psychTests;
    @ManyToMany
    @JoinTable(
            name = "pupil_grade",
            joinColumns = @JoinColumn(name = "pupil_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id")
    )
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}
