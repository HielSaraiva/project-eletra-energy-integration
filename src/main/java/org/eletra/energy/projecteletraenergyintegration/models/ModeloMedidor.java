package org.eletra.energy.projecteletraenergyintegration.models;

public class ModeloMedidor {

	private String nomeModeloMedidor;

	public ModeloMedidor(String nomeModeloMedidor) {
		this.nomeModeloMedidor = nomeModeloMedidor;
	}

	public String getNomeModeloMedidor() {
		return nomeModeloMedidor;
	}

	public void setNomeModeloMedidor(String nomeModeloMedidor) {
		this.nomeModeloMedidor = nomeModeloMedidor;
	}

	@Override
	public String toString() {
		return nomeModeloMedidor;
	}
}
