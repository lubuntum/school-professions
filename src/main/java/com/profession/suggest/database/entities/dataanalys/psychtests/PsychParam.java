package com.profession.suggest.database.entities.dataanalys.psychtests;


import com.profession.suggest.database.entities.dataanalys.clusters.Cluster;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "psych_param")
public class PsychParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer param;
    //Relationship to PsychParamName ManyToOne +
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "psych_param_name_id")
    private PsychParamName psychParamName;
    //Possible but not necessary Cluster (if use there for associated psych score)
    /**Even here is many to many that is only because params use in clusters also, that why */
    @ManyToMany(mappedBy = "psychParams")
    private List<PsychTest> psychTest;
    /**Even here is many to many that only because params use in psychTest also,
     * in future there is a copmare and search in pupil's params and clusters params that is important
     * and that's why psychParams can have psychTest or cluster BUT NOT BOTH they use for different purpose
     * and there ALWAYS 100% guarantee one item in psychTest List or cluster list that why name in single number*/
    @ManyToMany(mappedBy = "psychParams")
    private List<Cluster> cluster;
}
