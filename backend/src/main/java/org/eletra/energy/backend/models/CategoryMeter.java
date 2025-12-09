package org.eletra.energy.backend.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "meter_category")
@Data
@NoArgsConstructor
public class CategoryMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade =  CascadeType.ALL)
    private List<ModelMeter> meterModels;

    @ManyToOne
    @JoinColumn(name = "line_id", nullable = false)
    private LineMeter line;

    public CategoryMeter(String name, LineMeter line) {
        this.name = name;
        this.line = line;
    }
}