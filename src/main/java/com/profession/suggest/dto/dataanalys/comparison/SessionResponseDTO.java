package com.profession.suggest.dto.dataanalys.comparison;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponseDTO {
    private Long id;
    private String clientSessionId;
    private Long collectionId;
    private String collectionName;
    private String filePath;
    private Integer recordCount;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private Double totalDurationSeconds;
    private Boolean isCompleted;
    private String uploadStatus;
    private LocalDateTime createdAt;
}