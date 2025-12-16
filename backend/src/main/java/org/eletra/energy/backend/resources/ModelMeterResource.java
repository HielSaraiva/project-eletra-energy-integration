package org.eletra.energy.backend.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eletra.energy.backend.models.ModelMeter;
import org.eletra.energy.backend.repositories.ModelMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Meter Models")
@CrossOrigin(origins = "*")
public class ModelMeterResource {

    @Autowired
    private ModelMeterRepository modelMeterRepository;

    @GetMapping(value = "/meter-models")
    @ApiOperation(value = "Returns a list of Meter Models")
    public List<ModelMeter> listMeterModels() {
        return modelMeterRepository.findAll();
    }

    @GetMapping(value = "/meter-model/{id}")
    @ApiOperation(value = "Returns a Meter Model by ID")
    public ModelMeter listMeterModelsById(@PathVariable UUID id) {
        return modelMeterRepository.findById(id).get();
    }
}
