package it61.springlabs.data.dto.vps;

import java.util.UUID;

public class VpsReadDto {
    private UUID id;
    private UUID userId;
    private String operatingSystem;
    private Integer CPUCount;
    private Double CPURate;
    private Double RAM;

    public VpsReadDto(UUID id, UUID userId, String operatingSystem, Integer CPUCount, Double CPURate, Double RAM) {
        this.id = id;
        this.userId = userId;
        this.operatingSystem = operatingSystem;
        this.CPUCount = CPUCount;
        this.CPURate = CPURate;
        this.RAM = RAM;
    }

    public VpsReadDto() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public Integer getCPUCount() {
        return CPUCount;
    }

    public Double getCPURate() {
        return CPURate;
    }

    public Double getRAM() {
        return RAM;
    }
}
