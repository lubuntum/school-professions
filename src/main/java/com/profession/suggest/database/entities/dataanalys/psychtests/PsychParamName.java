package com.profession.suggest.database.entities.dataanalys.psychtests;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "psych_param_name")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class PsychParamName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    //OneToMany PsychParam +
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PsychParam> params;
}
