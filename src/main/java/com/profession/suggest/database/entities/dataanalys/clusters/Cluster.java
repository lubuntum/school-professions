package com.profession.suggest.database.entities.dataanalys.clusters;

import com.profession.suggest.database.entities.dataanalys.psychtests.PsychParam;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cluster")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cluster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private Integer number;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;
    //OneToMany List PsychResultParams - list of associated psych params as neirotizm 12 and etc
    @ManyToMany
    @JoinTable(
            name = "cluster_param",
            joinColumns = @JoinColumn(name = "cluster_id"),
            inverseJoinColumns = @JoinColumn(name = "psych_param_id")
    )
    private List<PsychParam> psychParams;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}
