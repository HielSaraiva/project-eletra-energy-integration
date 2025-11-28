package application.models;

import java.util.ArrayList;

public class CategoriaMedidor {

	private String nomeCategoriaMedidor;
	private ArrayList<ModeloMedidor> modelosMedidor;

	public CategoriaMedidor(String nomeCategoriaMedidor, ArrayList<ModeloMedidor> modelosMedidor) {
		this.nomeCategoriaMedidor = nomeCategoriaMedidor;
		this.modelosMedidor = modelosMedidor;
	}

	public String getNomeCategoriaMedidor() {
		return nomeCategoriaMedidor;
	}

	public void setNomeCategoriaMedidor(String nomeCategoriaMedidor) {
		this.nomeCategoriaMedidor = nomeCategoriaMedidor;
	}

	public ArrayList<ModeloMedidor> getModelosMedidor() {
		return modelosMedidor;
	}

	public void setModelosMedidor(ArrayList<ModeloMedidor> modelosMedidor) {
		this.modelosMedidor = modelosMedidor;
	}

	@Override
	public String toString() {
		return  nomeCategoriaMedidor;
	}
}
