package it61.springlabs.yetanotherlab.DTO;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class VpsDTO {
    @NotNull
    private String operatingSystem;
    private UUID owner_id;
    @NotNull
    private Integer CPUCount;
    @NotNull
    private Double CPURate;
    @NotNull
    private Double RAM;

    public VpsDTO(String operatingSystem, UUID owner, Integer cpuCount, Double cpuRate, Double ram) {
        this.operatingSystem = operatingSystem;
        this.owner_id = owner;
        this.CPUCount = cpuCount;
        this.CPURate = cpuRate;
        this.RAM = ram;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public UUID getOwner() {
        return owner_id;
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
