package application.models;

import java.util.ArrayList;

public class LineMeter {

	private String name;
	private ArrayList<CategoryMeter> meterCategories;

	public LineMeter(String name, ArrayList<CategoryMeter> meterCategories) {
		this.name = name;
		this.meterCategories = meterCategories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<CategoryMeter> getMeterCategories() {
		return meterCategories;
	}

	public void setMeterCategories(ArrayList<CategoryMeter> meterCategories) {
		this.meterCategories = meterCategories;
	}

	@Override
	public String toString() {
		return "LineMeter [name=" + name + ", meterCategories=" + meterCategories + "]";
	}
}
