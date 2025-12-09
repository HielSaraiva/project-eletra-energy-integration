package org.eletra.energy.backend.controllers;

import java.util.ArrayList;

import org.eletra.energy.backend.models.LineMeter;

public class BackController {
    // Atributos auxiliares
    private ArrayList<LineMeter> meterLines;

    // Métodos auxiliares
    public void loadMeterLines() {

    }

    public ArrayList<LineMeter> getMeterLines() {
        if (meterLines == null) {
            loadMeterLines();
        }
        return meterLines;
    }
}
