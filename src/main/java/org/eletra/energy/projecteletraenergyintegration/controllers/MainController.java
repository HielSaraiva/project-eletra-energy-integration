package org.eletra.energy.projecteletraenergyintegration.controllers;

import java.util.ArrayList;

import org.eletra.energy.projecteletraenergyintegration.models.CategoriaMedidor;
import org.eletra.energy.projecteletraenergyintegration.models.LinhaMedidor;
import org.eletra.energy.projecteletraenergyintegration.models.ModeloMedidor;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class MainController {
	// Componentes FXML
	@FXML
	private ComboBox<LinhaMedidor> comboBox;
	@FXML
	private TreeView<String> treeView;
	@FXML
	private TreeItem<String> treeItem;
	@FXML
	private TitledPane titledPaneLinhas;
	@FXML
	private TitledPane titledPaneModelos;

	// Atributos auxiliares
	private ArrayList<LinhaMedidor> linhasMedidores;
	private ArrayList<CategoriaMedidor> categoriasCronos;
	private ArrayList<CategoriaMedidor> categoriasAres;
	private ArrayList<ModeloMedidor> modelosCronosOld;
	private ArrayList<ModeloMedidor> modelosCronosL;
	private ArrayList<ModeloMedidor> modelosCronosNG;
	private ArrayList<ModeloMedidor> modelosAresTB;
	private ArrayList<ModeloMedidor> modelosAresTHS;

	// Métodos auxiliares
	private void instanciarModelos() {
		modelosCronosOld = new ArrayList<ModeloMedidor>();
		modelosCronosOld.add(new ModeloMedidor("Cronos 6001-A"));
		modelosCronosOld.add(new ModeloMedidor("Cronos 6003"));
		modelosCronosOld.add(new ModeloMedidor("Cronos 7023"));

		modelosCronosL = new ArrayList<ModeloMedidor>();
		modelosCronosL.add(new ModeloMedidor("Cronos 6021L"));
		modelosCronosL.add(new ModeloMedidor("Cronos 7023L"));

		modelosCronosNG = new ArrayList<ModeloMedidor>();
		modelosCronosNG.add(new ModeloMedidor("Cronos 6001-NG"));
		modelosCronosNG.add(new ModeloMedidor("Cronos 6003-NG"));
		modelosCronosNG.add(new ModeloMedidor("Cronos 6021-NG"));
		modelosCronosNG.add(new ModeloMedidor("Cronos 6031-NG"));
		modelosCronosNG.add(new ModeloMedidor("Cronos 7021-NG"));
		modelosCronosNG.add(new ModeloMedidor("Cronos 7023-NG"));

		modelosAresTB = new ArrayList<ModeloMedidor>();
		modelosAresTB.add(new ModeloMedidor("ARES 7021"));
		modelosAresTB.add(new ModeloMedidor("ARES 7031"));
		modelosAresTB.add(new ModeloMedidor("ARES 7023"));

		modelosAresTHS = new ArrayList<ModeloMedidor>();
		modelosAresTHS.add(new ModeloMedidor("ARES 8023 15"));
		modelosAresTHS.add(new ModeloMedidor("ARES 8023 200"));
		modelosAresTHS.add(new ModeloMedidor("ARES 8023 2,5"));
	}

	private void instanciarCategorias() {
		instanciarModelos();
		categoriasCronos = new ArrayList<CategoriaMedidor>();
		categoriasCronos.add(new CategoriaMedidor("Cronos Old", modelosCronosOld));
		categoriasCronos.add(new CategoriaMedidor("Cronos L", modelosCronosL));
		categoriasCronos.add(new CategoriaMedidor("Cronos-NG", modelosCronosNG));

		categoriasAres = new ArrayList<CategoriaMedidor>();
		categoriasAres.add(new CategoriaMedidor("Ares TB", modelosAresTB));
		categoriasAres.add(new CategoriaMedidor("Ares THS", modelosAresTHS));
	}

	private void instanciarLinhas() {
		instanciarCategorias();
		linhasMedidores = new ArrayList<LinhaMedidor>();
		linhasMedidores.add(new LinhaMedidor("Cronos", categoriasCronos));
		linhasMedidores.add(new LinhaMedidor("Ares", categoriasAres));
	}

	private void carregarComboBox() {
		comboBox.getItems().addAll(linhasMedidores);
	}

	// Métodos FXML
	@FXML
	public void initialize() {
		instanciarLinhas();
		carregarComboBox();
	}

	@FXML
	public void aoSelecionarLinha() {
		try {
			treeView.setShowRoot(false);

			LinhaMedidor linhaSelecionada = comboBox.getValue();
			if (linhaSelecionada.getNomeLinhaMedidor() == "Cronos") {
				// Settando as categorias no nó raiz
				ArrayList<TreeItem<String>> categorias = new ArrayList<>();

				for (CategoriaMedidor cat : linhasMedidores.get(0).getCategoriasMedidor()) {
					categorias.add(new TreeItem<>(cat.getNomeCategoriaMedidor()));
				}

				treeItem.getChildren().setAll(categorias);

				// Settando os modelos de cada categoria
				for (int i = 0; i < linhasMedidores.get(0).getCategoriasMedidor().size(); i++) {

					CategoriaMedidor categoria = linhasMedidores.get(0).getCategoriasMedidor().get(i);

					ArrayList<TreeItem<String>> modelos = new ArrayList<>();

					for (ModeloMedidor mod : categoria.getModelosMedidor()) {
						modelos.add(new TreeItem<>(mod.getNomeModeloMedidor()));
//						System.out.println(mod);
					}

//					System.out.println(modelos);

					treeItem.getChildren().get(i).getChildren().setAll(modelos);
					treeItem.getChildren().get(i).setExpanded(true);
				}

			} else if (linhaSelecionada.getNomeLinhaMedidor() == "Ares") {

				// Settando as categorias no nó raiz
				ArrayList<TreeItem<String>> categorias = new ArrayList<>();

				for (CategoriaMedidor cat : linhasMedidores.get(1).getCategoriasMedidor()) {
					categorias.add(new TreeItem<>(cat.getNomeCategoriaMedidor()));
				}

				treeItem.getChildren().setAll(categorias);

				// Settando os modelos de cada categoria
				for (int i = 0; i < linhasMedidores.get(1).getCategoriasMedidor().size(); i++) {

					CategoriaMedidor categoria = linhasMedidores.get(1).getCategoriasMedidor().get(i);

					ArrayList<TreeItem<String>> modelos = new ArrayList<>();

					for (ModeloMedidor mod : categoria.getModelosMedidor()) {
						modelos.add(new TreeItem<>(mod.getNomeModeloMedidor()));
//						System.out.println(mod);
					}

//					System.out.println(modelos);

					treeItem.getChildren().get(i).getChildren().setAll(modelos);
					treeItem.getChildren().get(i).setExpanded(true);
				}
			}
			treeItem.setExpanded(true);
			titledPaneModelos.setDisable(false);
			titledPaneModelos.setExpanded(true);

		} catch (Exception e) {
			System.out.println("Erro ao selecionar linha: " + e.getMessage());
		}
	}
}
