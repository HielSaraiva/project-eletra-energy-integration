package org.eletra.energy.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "meter_line")
@Data
@NoArgsConstructor
public class LineMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "line", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CategoryMeter> meterCategories;

    public LineMeter(String name) {
        this.name = name;
    }
}