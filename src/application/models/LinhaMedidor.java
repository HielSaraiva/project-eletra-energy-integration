package application.models;

import java.util.ArrayList;

public class LinhaMedidor {
	
	private String nomeLinhaMedidor;
	private ArrayList<CategoriaMedidor> categoriasMedidor;
	
	public LinhaMedidor(String nomeLinhaMedidor, ArrayList<CategoriaMedidor> categoriasMedidor) {
		this.nomeLinhaMedidor = nomeLinhaMedidor;
		this.categoriasMedidor = categoriasMedidor;
	}

	public String getNomeLinhaMedidor() {
		return nomeLinhaMedidor;
	}

	public void setNomeLinhaMedidor(String nomeLinhaMedidor) {
		this.nomeLinhaMedidor = nomeLinhaMedidor;
	}

	public ArrayList<CategoriaMedidor> getCategoriasMedidor() {
		return categoriasMedidor;
	}

	public void setCategoriasMedidor(ArrayList<CategoriaMedidor> categoriasMedidor) {
		this.categoriasMedidor = categoriasMedidor;
	}

	@Override
	public String toString() {
		return nomeLinhaMedidor;
	}
}
