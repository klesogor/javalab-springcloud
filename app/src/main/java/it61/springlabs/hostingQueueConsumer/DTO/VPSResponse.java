package it61.springlabs.hostingQueueConsumer.DTO;

import java.util.UUID;

public final class VPSResponse {
    private String operatingSystem;
    private UUID id;
    private Integer CPUCount;
    private Double CPURate;
    private Double RAM;

    public VPSResponse(UUID id, String operatingSystem, Integer cpuCount, Double cpuRate, Double ram) {
        this.id = id;
        this.operatingSystem = operatingSystem;
        CPUCount = cpuCount;
        CPURate = cpuRate;
        RAM = ram;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public UUID getId() {
        return id;
    }

    public Double getRAM() {
        return RAM;
    }

    public Double getCPURate() {
        return CPURate;
    }

    public Integer getCPUCount() {
        return CPUCount;
    }

    public VPSResponse setCPUCount(Integer CPUCount) {
        this.CPUCount = CPUCount;
        return this;
    }
}
