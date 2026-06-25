package com.profession.suggest.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HRResponse {
    private Long accountId;
    private Long companyId;
    private String email;
    private String fullName;
    private String companyName;

}
