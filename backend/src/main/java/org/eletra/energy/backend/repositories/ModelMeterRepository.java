package org.eletra.energy.backend.repositories;

import org.eletra.energy.backend.models.ModelMeter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModelMeterRepository extends JpaRepository<ModelMeter, UUID> {
}
