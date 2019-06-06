package it61.springlabs.data.dto.vps;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class VpsWriteDTO {
    private String operatingSystem;
    private UUID userId;
    private Integer cpuCount;
    private Double cpuRate;
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

    public Integer getСpuCount() {
        return cpuCount;
    }

    public Double getСpuRate() {
        return cpuRate;
    }

    public Double getRam() {
        return ram;
    }

}
