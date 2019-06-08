package it61.springlabs.data.dto.vps;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class VpsReadDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("userId")
    private UUID userId;
    @JsonProperty("operatingSystem")
    private String operatingSystem;
    @JsonProperty("cpucount")
    private Integer cpuCount;
    @JsonProperty("cpurate")
    private Double cpuRate;
    @JsonProperty("ram")
    private Double ram;

    public VpsReadDto(UUID id, UUID userId, String operatingSystem, Integer CPUCount, Double CPURate, Double RAM) {
        this.id = id;
        this.userId = userId;
        this.operatingSystem = operatingSystem;
        this.cpuCount = CPUCount;
        this.cpuRate = CPURate;
        this.ram = RAM;
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

    public Integer getCpuCount() {
        return cpuCount;
    }

    public Double getCpuRate() {
        return cpuRate;
    }

    public Double getRam() {
        return ram;
    }
}
