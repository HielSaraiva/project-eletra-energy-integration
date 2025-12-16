package org.eletra.energy.backend.repositories;

import org.eletra.energy.backend.models.CategoryMeter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryMeterRepository extends JpaRepository<CategoryMeter, UUID> {
}
