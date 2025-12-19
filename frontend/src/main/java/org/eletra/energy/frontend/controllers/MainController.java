package org.eletra.energy.frontend.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.eletra.energy.frontend.Main;
import org.eletra.energy.frontend.models.dtos.CategoryMeterDTO;
import org.eletra.energy.frontend.models.dtos.LineMeterDTO;
import org.eletra.energy.frontend.models.dtos.ModelMeterDTO;
import org.eletra.energy.frontend.services.ApiCategoryMeterService;
import org.eletra.energy.frontend.services.ApiLineMeterService;
import org.eletra.energy.frontend.services.ApiModelMeterService;
//import org.eletra.energy.backend.controllers.BackController;
//import org.eletra.energy.backend.models.LineMeter;
//import org.eletra.energy.backend.models.CategoryMeter;
//import org.eletra.energy.backend.models.ModelMeter;

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
    private List<CategoryMeterDTO> categoryMeters;
    private List<LineMeterDTO> lineMeters;
    private List<ModelMeterDTO> modelMeters;

    private ApiLineMeterService apiLineMeterService;
    private ApiCategoryMeterService apiCategoryMeterService;
    private ApiModelMeterService apiModelMeterService;

    // Construtor
    public MainController() {
        this.apiLineMeterService = new ApiLineMeterService();
        this.apiCategoryMeterService = new ApiCategoryMeterService();
        this.apiModelMeterService = new ApiModelMeterService();
    }

    // Métodos auxiliares
    private void loadComboBox() {

        ArrayList<String> meterLinesNames = lineMeters.stream()
                .map(LineMeterDTO::getName)
                .collect(Collectors.toCollection(ArrayList::new));

        comboBox.getItems().addAll(meterLinesNames);
    }

    private void loadTreeItem(String loadLine) {

        LineMeterDTO selectedLine = lineMeters.stream()
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

        for (CategoryMeterDTO category : selectedLine.getMeterCategories()) {

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
        try {
            lineMeters = apiLineMeterService.getLineMeters("meter-lines");
            categoryMeters = apiCategoryMeterService.getCategoryMeters("meter-categories");
            modelMeters = apiModelMeterService.getModelMeters("meter-models");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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

    // Getters e Setters
    public ComboBox<String> getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public TreeView<String> getTreeView() {
        return treeView;
    }

    public void setTreeView(TreeView<String> treeView) {
        this.treeView = treeView;
    }

    public TreeItem<String> getTreeItem() {
        return treeItem;
    }

    public void setTreeItem(TreeItem<String> treeItem) {
        this.treeItem = treeItem;
    }

    public TitledPane getTitledPaneModelos() {
        return titledPaneModelos;
    }

    public void setTitledPaneModelos(TitledPane titledPaneModelos) {
        this.titledPaneModelos = titledPaneModelos;
    }

    public TitledPane getTitledPaneLinhas() {
        return titledPaneLinhas;
    }

    public void setTitledPaneLinhas(TitledPane titledPaneLinhas) {
        this.titledPaneLinhas = titledPaneLinhas;
    }

    public List<CategoryMeterDTO> getCategoryMeters() {
        return categoryMeters;
    }

    public void setCategoryMeters(List<CategoryMeterDTO> categoryMeters) {
        this.categoryMeters = categoryMeters;
    }

    public List<LineMeterDTO> getLineMeters() {
        return lineMeters;
    }

    public void setLineMeters(List<LineMeterDTO> lineMeters) {
        this.lineMeters = lineMeters;
    }

    public List<ModelMeterDTO> getModelMeters() {
        return modelMeters;
    }

    public void setModelMeters(List<ModelMeterDTO> modelMeters) {
        this.modelMeters = modelMeters;
    }

    public ApiLineMeterService getApiLineMeterService() {
        return apiLineMeterService;
    }

    public void setApiLineMeterService(ApiLineMeterService apiLineMeterService) {
        this.apiLineMeterService = apiLineMeterService;
    }

    public ApiCategoryMeterService getApiCategoryMeterService() {
        return apiCategoryMeterService;
    }

    public void setApiCategoryMeterService(ApiCategoryMeterService apiCategoryMeterService) {
        this.apiCategoryMeterService = apiCategoryMeterService;
    }

    public ApiModelMeterService getApiModelMeterService() {
        return apiModelMeterService;
    }

    public void setApiModelMeterService(ApiModelMeterService apiModelMeterService) {
        this.apiModelMeterService = apiModelMeterService;
    }
}
