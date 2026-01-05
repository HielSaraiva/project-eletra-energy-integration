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
import org.eletra.energy.frontend.ui.UILoader;
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
    private ApiLineMeterService apiLineMeterService;
    private ApiCategoryMeterService apiCategoryMeterService;
    private ApiModelMeterService apiModelMeterService;

    private UILoader uiLoader;

    // Construtor
    public MainController() {
        this.apiLineMeterService = new ApiLineMeterService();
        this.apiCategoryMeterService = new ApiCategoryMeterService();
        this.apiModelMeterService = new ApiModelMeterService();
    }

    // Métodos FXML
    @FXML
    public void initialize() {
        try {
            List<LineMeterDTO> lineMeters = apiLineMeterService.getLineMeters("meter-lines");
            List<CategoryMeterDTO> categoryMeters = apiCategoryMeterService.getCategoryMeters("meter-categories");
            List<ModelMeterDTO> modelMeters = apiModelMeterService.getModelMeters("meter-models");

            uiLoader = new UILoader(lineMeters, categoryMeters, modelMeters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        titledPaneModelos.setDisable(true);
        uiLoader.loadComboBox(comboBox);
    }

    @FXML
    public void onLineSelect() {

        treeView.setShowRoot(false);

        String selectedLineName = comboBox.getValue();
        uiLoader.loadTreeItem(treeItem, selectedLineName);

        treeItem.setExpanded(true);
        titledPaneModelos.setDisable(false);
        titledPaneModelos.setExpanded(true);
    }

    // Getters e Setters
    public ComboBox<String> getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox<String> comboBox) {
        this.comboBox = comboBox;
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

    public void setTitledPaneLinhas(TitledPane titledPaneLinhas) {
        this.titledPaneLinhas = titledPaneLinhas;
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

    public UILoader getUiLoader() {
        return uiLoader;
    }

    public void setUiLoader(UILoader uiLoader) {
        this.uiLoader = uiLoader;
    }
}
