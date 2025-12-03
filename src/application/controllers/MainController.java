package application.controllers;

import java.util.ArrayList;

import application.models.CategoryMeter;
import application.models.LineMeter;
import application.models.ModelMeter;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

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

	private void instantiateLines() {
		instantiateCategories();
		meterLines = new ArrayList<LineMeter>();
		meterLines.add(new LineMeter("Cronos", cronosCategories));
		meterLines.add(new LineMeter("Ares", aresCategories));
	}

	private void loadComboBox() {
		ArrayList<String> meterLinesNames = new ArrayList<String>();

		for (LineMeter meter : meterLines) {
			meterLinesNames.add(meter.getName());
		}

		comboBox.getItems().addAll(meterLinesNames);
	}

	private void loadTreeItem(String loadLine) {

		int j = 0;

		for (LineMeter line : meterLines) {
			if (loadLine.equals(line.getName())) {
				break;
			}
			++j;
		}

		ArrayList<TreeItem<String>> categories = new ArrayList<>();

		for (CategoryMeter cat : meterLines.get(j).getMeterCategories()) {
			categories.add(new TreeItem<>(cat.getName()));
		}

		treeItem.getChildren().setAll(categories);

		for (int i = 0; i < meterLines.get(j).getMeterCategories().size(); i++) {

			CategoryMeter category = meterLines.get(j).getMeterCategories().get(i);

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
		instantiateLines();
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
