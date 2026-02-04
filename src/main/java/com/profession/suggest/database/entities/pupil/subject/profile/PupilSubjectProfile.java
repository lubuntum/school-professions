package com.profession.suggest.database.entities.pupil.subject.profile;

import com.profession.suggest.database.entities.pupil.Pupil;
import com.profession.suggest.database.entities.pupil.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pupil_subject_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PupilSubjectProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "interest_level_id")
    InterestLevel interestLevel;
    @ManyToOne
    @JoinColumn(name = "practical_project_id")
    ParticipationLevel practicalProject;
    @ManyToOne
    @JoinColumn(name = "contest_id")
    ParticipationLevel contest;
    @ManyToOne
    @JoinColumn(name = "selection_probability_id")
    ProbabilityLevel probabilityLevel;
    @ManyToOne
    @JoinColumn(name = "pupil_id", nullable = false)
    Pupil pupil;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    Subject subject;
}
