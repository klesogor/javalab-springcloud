package it61.springlabs.data.dto.vps;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class VpsWriteDTO {
    @NotNull
    private String operatingSystem;
    private UUID owner_id;
    @NotNull
    private Integer CpuCount;
    @NotNull
    private Double  CpuRate;
    @NotNull
    private Double Ram;

    public VpsWriteDTO(String operatingSystem, UUID owner, Integer cpuCount, Double cpuRate, Double ram) {
        this.operatingSystem = operatingSystem;
        this.owner_id = owner;
        this.CpuCount = cpuCount;
        this.CpuRate = cpuRate;
        this.Ram = ram;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public UUID  getOwner() {
        return owner_id;
    }

    public Integer getCpuCount() {
        return CpuCount;
    }

    public Double  getCpuRate() {
        return CpuRate;
    }

    public Double getRam() {
        return Ram;
    }

}