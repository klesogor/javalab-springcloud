package it61.springlabs.data.dto.vps;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class VpsWriteDTO {
    @NotNull
    private String operatingSystem;
    private UUID owner_id;
    @NotNull
    private Integer cpuCount;
    @NotNull
    private Double cpuRate;
    @NotNull
    private Double ram;

    public VpsWriteDTO(String operatingSystem, UUID owner, Integer cpuCount, Double cpuRate, Double ram) {
        this.operatingSystem = operatingSystem;
        this.owner_id = owner;
        this.cpuCount = cpuCount;
        this.cpuRate = cpuRate;
        this.ram = ram;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public UUID  getOwner() {
        return owner_id;
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
