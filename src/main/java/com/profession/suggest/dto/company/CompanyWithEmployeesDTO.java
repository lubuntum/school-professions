package com.profession.suggest.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyWithEmployeesDTO {
    private Long id;
    private String name;
    private String inn;
    private String ogrn;
    private String address;
    private String phone;
    private String email;
    private List<Employee> employees;
    private Integer employeesCount;
}
