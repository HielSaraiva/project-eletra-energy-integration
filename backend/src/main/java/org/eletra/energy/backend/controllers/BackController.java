package org.eletra.energy.backend.controllers;

import java.util.ArrayList;

import org.eletra.energy.backend.models.CategoryMeter;
import org.eletra.energy.backend.models.LineMeter;
import org.eletra.energy.backend.models.ModelMeter;

public class BackController {
	// Atributos auxiliares
	private ArrayList<LineMeter> meterLines;
	private ArrayList<CategoryMeter> cronosCategories;
	private ArrayList<CategoryMeter> aresCategories;
	private ArrayList<ModelMeter> cronosOldModels;
	private ArrayList<ModelMeter> cronosLModels;
	private ArrayList<ModelMeter> cronosNGModels;
	private ArrayList<ModelMeter> aresTBModels;
	private ArrayList<ModelMeter> aresTHSModels;

	// Métodos auxiliares
	private void instantiateModels() {
		cronosOldModels = new ArrayList<ModelMeter>();
		cronosOldModels.add(new ModelMeter("Cronos 6001-A"));
		cronosOldModels.add(new ModelMeter("Cronos 6003"));
		cronosOldModels.add(new ModelMeter("Cronos 7023"));

		cronosLModels = new ArrayList<ModelMeter>();
		cronosLModels.add(new ModelMeter("Cronos 6021L"));
		cronosLModels.add(new ModelMeter("Cronos 7023L"));

		cronosNGModels = new ArrayList<ModelMeter>();
		cronosNGModels.add(new ModelMeter("Cronos 6001-NG"));
		cronosNGModels.add(new ModelMeter("Cronos 6003-NG"));
		cronosNGModels.add(new ModelMeter("Cronos 6021-NG"));
		cronosNGModels.add(new ModelMeter("Cronos 6031-NG"));
		cronosNGModels.add(new ModelMeter("Cronos 7021-NG"));
		cronosNGModels.add(new ModelMeter("Cronos 7023-NG"));

		aresTBModels = new ArrayList<ModelMeter>();
		aresTBModels.add(new ModelMeter("ARES 7021"));
		aresTBModels.add(new ModelMeter("ARES 7031"));
		aresTBModels.add(new ModelMeter("ARES 7023"));

		aresTHSModels = new ArrayList<ModelMeter>();
		aresTHSModels.add(new ModelMeter("ARES 8023 15"));
		aresTHSModels.add(new ModelMeter("ARES 8023 200"));
		aresTHSModels.add(new ModelMeter("ARES 8023 2,5"));
	}

	private void instantiateCategories() {
		instantiateModels();
		cronosCategories = new ArrayList<CategoryMeter>();
		cronosCategories.add(new CategoryMeter("Cronos Old", cronosOldModels));
		cronosCategories.add(new CategoryMeter("Cronos L", cronosLModels));
		cronosCategories.add(new CategoryMeter("Cronos-NG", cronosNGModels));

		aresCategories = new ArrayList<CategoryMeter>();
		aresCategories.add(new CategoryMeter("Ares TB", aresTBModels));
		aresCategories.add(new CategoryMeter("Ares THS", aresTHSModels));
	}

	public void instantiateLines() {
		instantiateCategories();
		meterLines = new ArrayList<LineMeter>();
		meterLines.add(new LineMeter("Cronos", cronosCategories));
		meterLines.add(new LineMeter("Ares", aresCategories));
	}

    public ArrayList<LineMeter> getMeterLines() {
        return meterLines;
    }
}
