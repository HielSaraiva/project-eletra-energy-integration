package org.eletra.energy.frontend.models.dtos;

import java.util.UUID;


public class ModelMeterDTO {

    private UUID id;
    private String name;
    private CategoryMeterDTO category;

    public ModelMeterDTO(UUID id, String name, CategoryMeterDTO category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryMeterDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryMeterDTO category) {
        this.category = category;
    }
}