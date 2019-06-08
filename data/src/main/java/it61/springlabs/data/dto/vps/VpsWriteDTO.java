package it61.springlabs.data.dto.vps;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class VpsWriteDTO {
    @JsonProperty("operatingSystem")
    private String operatingSystem;
    @JsonProperty("userId")
    private UUID userId;
    @JsonProperty("cpucount")
    private Integer cpuCount;
    @JsonProperty("cpurate")
    private Double cpuRate;
    @JsonProperty("ram")
    private Double ram;

    public VpsWriteDTO(String operatingSystem, UUID userId, Integer cpuCount, Double cpuRate, Double ram) {
        this.operatingSystem = operatingSystem;
        this.userId = userId;
        this.cpuCount = cpuCount;
        this.cpuRate = cpuRate;
        this.ram = ram;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public UUID getUserId() {
        return userId;
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
