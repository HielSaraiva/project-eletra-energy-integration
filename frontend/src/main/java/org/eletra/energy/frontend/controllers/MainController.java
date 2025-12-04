package org.eletra.energy.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.eletra.energy.backend.controllers.BackController;
import org.eletra.energy.backend.models.CategoryMeter;
import org.eletra.energy.backend.models.LineMeter;
import org.eletra.energy.backend.models.ModelMeter;

import java.util.ArrayList;

public class MainController {
	// Componentes FXML
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private TreeView<String> treeView;
	@FXML
	private TreeItem<String> treeItem;
	@FXML
	private TitledPane titledPaneLinhas;
	@FXML
	private TitledPane titledPaneModelos;

    // Atributos auxiliares
    private BackController backController;

	private void loadComboBox() {
		ArrayList<String> meterLinesNames = new ArrayList<String>();

		for (LineMeter meter : backController.getMeterLines()) {
			meterLinesNames.add(meter.getName());
		}

		comboBox.getItems().addAll(meterLinesNames);
	}

	private void loadTreeItem(String loadLine) {

		int j = 0;

		for (LineMeter line : backController.getMeterLines()) {
			if (loadLine.equals(line.getName())) {
				break;
			}
			++j;
		}

		ArrayList<TreeItem<String>> categories = new ArrayList<>();

		for (CategoryMeter cat : backController.getMeterLines().get(j).getMeterCategories()) {
			categories.add(new TreeItem<>(cat.getName()));
		}

		treeItem.getChildren().setAll(categories);

		for (int i = 0; i < backController.getMeterLines().get(j).getMeterCategories().size(); i++) {

			CategoryMeter category = backController.getMeterLines().get(j).getMeterCategories().get(i);

			ArrayList<TreeItem<String>> models = new ArrayList<>();

			for (ModelMeter mod : category.getMeterModels()) {
				models.add(new TreeItem<>(mod.getName()));
			}

			treeItem.getChildren().get(i).getChildren().setAll(models);
			treeItem.getChildren().get(i).setExpanded(true);
		}
	}

	// Métodos FXML
	@FXML
	public void initialize() {
        backController = new BackController();
		backController.instantiateLines();
		loadComboBox();
	}

	@FXML
	public void onLineSelect() {
		try {
			treeView.setShowRoot(false);

			String selectedLineName = comboBox.getValue();

			loadTreeItem(selectedLineName);

			treeItem.setExpanded(true);
			titledPaneModelos.setDisable(false);
			titledPaneModelos.setExpanded(true);

		} catch (Exception e) {
			System.out.println("Error on line select: " + e.getMessage());
		}
	}
}
