package com.profession.suggest.dto.dataanalys.comparison;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleDTO {
    private Long id;
    private String sampleName;
    private String imagePath;
    private String description;
    private Integer displayOrder;
}
