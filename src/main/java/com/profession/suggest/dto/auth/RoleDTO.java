package com.profession.suggest.dto.auth;

import com.profession.suggest.database.entities.auth.role.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private RoleEnum name;
}
