package org.eletra.energy.frontend.ui;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import org.eletra.energy.frontend.models.dtos.CategoryMeterDTO;
import org.eletra.energy.frontend.models.dtos.LineMeterDTO;
import org.eletra.energy.frontend.models.dtos.ModelMeterDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UILoader {
    private List<LineMeterDTO> lineMeters;
    private List<CategoryMeterDTO> categoryMeters;
    private List<ModelMeterDTO> modelMeters;

    public UILoader(List<LineMeterDTO> lineMeters, List<CategoryMeterDTO> categoryMeters, List<ModelMeterDTO> modelMeters) {
        this.lineMeters = lineMeters;
        this.categoryMeters = categoryMeters;
        this.modelMeters = modelMeters;
    }

    public void loadComboBox(ComboBox<String> comboBox) {

        ArrayList<String> meterLinesNames = getMeterLinesNames();

        comboBox.getItems().addAll(meterLinesNames);
    }

    public void loadTreeItem(TreeItem<String> treeItem, String selectedLineName) {

        LineMeterDTO selectedLine = findLineByName(selectedLineName);
        List<CategoryMeterDTO> filteredCategories = filterCategoriesByLine(selectedLine);
        ArrayList<TreeItem<String>> categoryItems = createCategoryItems(filteredCategories);

        treeItem.getChildren().setAll(categoryItems);

        for (CategoryMeterDTO category : filteredCategories) {
            ArrayList<TreeItem<String>> modelItems = createModelItems(category);

            int idx = filteredCategories.indexOf(category);
            TreeItem<String> categoryTreeItem = treeItem.getChildren().get(idx);

            categoryTreeItem.getChildren().setAll(modelItems);
            categoryTreeItem.setExpanded(true);
        }
    }

    private ArrayList<String> getMeterLinesNames() {
        return lineMeters.stream()
                .map(LineMeterDTO::getName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private LineMeterDTO findLineByName(String name) {
        return lineMeters.stream()
                .filter(line -> name.equals(line.getName()))
                .findFirst()
                .orElse(null);
    }

    private List<CategoryMeterDTO> filterCategoriesByLine(LineMeterDTO line) {
        return categoryMeters.stream()
                .filter(cat -> line.getName().equals(cat.getLine().getName()))
                .collect(Collectors.toList());
    }

    private ArrayList<TreeItem<String>> createCategoryItems(List<CategoryMeterDTO> categories) {
        return categories.stream()
                .map(cat -> new TreeItem<>(cat.getName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<TreeItem<String>> createModelItems(CategoryMeterDTO category) {
        return modelMeters.stream()
                .filter(model -> category.getName().equals(model.getCategory().getName()))
                .map(model -> new TreeItem<>(model.getName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void setLineMeters(List<LineMeterDTO> lineMeters) {
        this.lineMeters = lineMeters;
    }

    public void setCategoryMeters(List<CategoryMeterDTO> categoryMeters) {
        this.categoryMeters = categoryMeters;
    }

    public void setModelMeters(List<ModelMeterDTO> modelMeters) {
        this.modelMeters = modelMeters;
    }
}