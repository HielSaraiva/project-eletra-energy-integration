package org.eletra.energy.frontend.models.dtos;

import java.util.List;
import java.util.UUID;

public class CategoryMeterDTO {

    private UUID id;
    private String name;
    private List<ModelMeterDTO> meterModels;
    private LineMeterDTO line;

    public CategoryMeterDTO(UUID id, String name, LineMeterDTO line) {
        this.id = id;
        this.name = name;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ModelMeterDTO> getMeterModels() {
        return meterModels;
    }

    public void setMeterModels(List<ModelMeterDTO> meterModels) {
        this.meterModels = meterModels;
    }

    public LineMeterDTO getLine() {
        return line;
    }

    public void setLine(LineMeterDTO line) {
        this.line = line;
    }
}