package com.profession.suggest.database.entities.pupil.subject;

import com.profession.suggest.database.entities.pupil.Pupil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pupil_grade",
    uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_pupil_subject_class",
                columnNames = {"pupil_id", "subject_id", "class_number"}
        )
    })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PupilGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pupil_id", nullable = false)
    private Pupil pupil;
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    @Column(name = "grade")
    private String grade;
    @Column(name = "class_number")
    private Integer classNumber;
}
