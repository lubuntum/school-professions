package com.profession.suggest.database.entities.dataanalys.psychtests;

import com.profession.suggest.database.entities.pupil.Pupil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "psych_test")
public class PsychTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "completion_time_seconds")
    private Double completionTimeSeconds;
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;
    //ManyToOne Pupil +
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pupil_id")
    private Pupil pupil;
    //manyToOne PsychTestType +
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psych_test_type_id")
    private PsychTestType psychTestType;
    //ManyToMany List PsychResultParam +
    @ManyToMany()
    @JoinTable(
            name = "psych_test_param",
            joinColumns = @JoinColumn(name = "psych_test_id"),
            inverseJoinColumns = @JoinColumn(name = "psych_param_id")
    )
    private Set<PsychParam> psychParams = new HashSet<>();
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

}
