package org.eletra.energy.backend.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eletra.energy.backend.models.LineMeter;
import org.eletra.energy.backend.repositories.LineMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Meter Lines")
@CrossOrigin(origins = "*")
public class LineMeterResource {

    @Autowired
    private LineMeterRepository lineMeterRepository;

    @GetMapping(value = "/meter-lines")
    @ApiOperation(value = "Returns a list of Meter Lines")
    public List<LineMeter> listMeterLines() {
        return lineMeterRepository.findAll();
    }

    @GetMapping(value = "/meter-line/{id}")
    @ApiOperation(value = "Returns a Meter Line by ID")
    public LineMeter listMeterLinesById(@PathVariable UUID id) {
        return lineMeterRepository.findById(id).get();
    }
}
