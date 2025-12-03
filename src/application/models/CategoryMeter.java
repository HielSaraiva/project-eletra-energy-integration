package application.models;

import java.util.ArrayList;

public class CategoryMeter {

	private String name;
	private ArrayList<ModelMeter> meterModels;

	public CategoryMeter(String name, ArrayList<ModelMeter> meterModels) {
		this.name = name;
		this.meterModels = meterModels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ModelMeter> getMeterModels() {
		return meterModels;
	}

	public void setMeterModels(ArrayList<ModelMeter> meterModels) {
		this.meterModels = meterModels;
	}

	@Override
	public String toString() {
		return "CategoryMeter [name=" + name + ", meterModels=" + meterModels + "]";
	}
}
