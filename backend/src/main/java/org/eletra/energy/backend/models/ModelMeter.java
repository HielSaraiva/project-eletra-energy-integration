package org.eletra.energy.backend.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "meter_model")
@Data
@NoArgsConstructor
public class ModelMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name= "category_id", nullable = false)
    private CategoryMeter category;

    public ModelMeter(String name, CategoryMeter category) {
        this.name = name;
        this.category = category;
    }
}