package com.profession.suggest.database.entities.gender;

import com.profession.suggest.database.entities.pupil.Pupil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "gender")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private GenderEnum name;
    @OneToMany(mappedBy = "gender")
    private List<Pupil> pupils;
}
