package com.profession.suggest.dto.dataanalys.comparison;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionSubmitRequest {
    private String clientSessionId;
    private Long collectionId;
    private String appVersion;
    private String dataSchemaVersion;
    private String dataSourceMode;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private Double totalDurationSeconds;
    private Boolean isCompleted;
    private Boolean eyeTrackingAvailable;
    private Boolean faceTrackingAvailable;
    private String uploadStatus;
    private Integer recordCount;
    private MultipartFile dataFile;
}
