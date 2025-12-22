package org.eletra.energy.frontend.models.dtos;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class LineMeterDTO {

    private UUID id;
    private String name;
    private List<CategoryMeterDTO> meterCategories;

    public LineMeterDTO() {

    }

    public LineMeterDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryMeterDTO> getMeterCategories() {
        return meterCategories;
    }

    public void setMeterCategories(List<CategoryMeterDTO> meterCategories) {
        this.meterCategories = meterCategories;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}