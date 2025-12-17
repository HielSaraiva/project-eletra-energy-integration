package org.eletra.energy.backend.repositories;

import org.eletra.energy.backend.models.LineMeter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LineMeterRepository extends JpaRepository<LineMeter, UUID> {
}
