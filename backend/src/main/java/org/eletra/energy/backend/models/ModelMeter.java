package org.eletra.energy.backend.models;

public class ModelMeter {

	private String name;

	public ModelMeter(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ModelMeter [name=" + name + "]";
	}
}
