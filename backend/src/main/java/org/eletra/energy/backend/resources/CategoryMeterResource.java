package org.eletra.energy.backend.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eletra.energy.backend.models.CategoryMeter;
import org.eletra.energy.backend.repositories.CategoryMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Meter Categories")
@CrossOrigin(origins = "*")
public class CategoryMeterResource {

    @Autowired
    private CategoryMeterRepository categoryMeterRepository;

    @GetMapping(value = "/meter-categories")
    @ApiOperation(value = "Returns a list of Meter Categories")
    public List<CategoryMeter> listMeterCategories() {
        return categoryMeterRepository.findAll();
    }

    @GetMapping(value = "/meter-category/{id}")
    @ApiOperation(value = "Returns a Meter Category by ID")
    public CategoryMeter listMeterCategoriesById(@PathVariable UUID id) {
        return categoryMeterRepository.findById(id).get();
    }
}
