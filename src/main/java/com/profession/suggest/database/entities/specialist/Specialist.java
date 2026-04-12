package com.profession.suggest.database.entities.specialist;

import com.profession.suggest.database.entities.auth.Account;
import com.profession.suggest.database.entities.gender.Gender;
import com.profession.suggest.database.entities.professions.Profession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "specialist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Specialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "contactEmail")
    private String contactEmail;
    @Column(name = "contactPhone")
    private String contactPhone;
    @Column(name = "experience")
    private String experience;
    @Column(name = "jobSatisfaction")
    private String jobSatisfaction;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", unique = true)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id")
    private Profession profession;
    @ManyToOne()
    @JoinColumn(name = "gender_id")
    private Gender gender;
}
