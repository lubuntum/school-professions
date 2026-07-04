package com.profession.suggest.dto.dataanalys.comparison;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollectionResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
    private String appVersion;
    private String dataSchemaVersion;
    private List<SampleDTO> samples;
}
