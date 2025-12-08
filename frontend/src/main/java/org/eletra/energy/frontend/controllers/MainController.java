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
import java.util.List;
import java.util.stream.Collectors;

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

        ArrayList<String> meterLinesNames = backController.getMeterLines().stream()
                .map(LineMeter::getName)
                .collect(Collectors.toCollection(ArrayList::new));

        comboBox.getItems().addAll(meterLinesNames);
    }

    private void loadTreeItem(String loadLine) {

        LineMeter selectedLine = backController.getMeterLines().stream()
                .filter(line -> loadLine.equals(line.getName()))
                .findFirst()
                .orElse(null);

        if (selectedLine == null) {
            throw new IllegalArgumentException("Line not found");
        }

        ArrayList<TreeItem<String>> categories = selectedLine.getMeterCategories().stream()
                .map(cat -> new TreeItem<>(cat.getName()))
                .collect(Collectors.toCollection(ArrayList::new));

        treeItem.getChildren().setAll(categories);

        for (CategoryMeter category : selectedLine.getMeterCategories()) {

            List<TreeItem<String>> modelItems = category.getMeterModels().stream()
                    .map(mod -> new TreeItem<>(mod.getName()))
                    .collect(Collectors.toList());

            TreeItem<String> categoryTreeItem = treeItem.getChildren().get(
                    selectedLine.getMeterCategories().indexOf(category)
            );

            categoryTreeItem.getChildren().setAll(modelItems);
            categoryTreeItem.setExpanded(true);
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
        } catch (IllegalArgumentException iae) {
            System.out.println("Error on load TreeItem: " + iae.getMessage());

        } catch (Exception e) {
            System.out.println("Error on line select: " + e.getMessage());
        }
    }
}
