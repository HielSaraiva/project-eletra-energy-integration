package org.eletra.energy.frontend.ui;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import org.eletra.energy.frontend.models.dtos.CategoryMeterDTO;
import org.eletra.energy.frontend.models.dtos.LineMeterDTO;
import org.eletra.energy.frontend.models.dtos.ModelMeterDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UILoaderTest extends ApplicationTest {

    private UILoader uiLoader;

    @Before
    public void setUp() {

        uiLoader = spy(new UILoader(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        uiLoader.setLineMeters(mockLineMeters());
        uiLoader.setCategoryMeters(mockCategoryMeters(0));
        uiLoader.setModelMeters(mockModelMeters());
    }

    @After
    public void tearDown() {

        uiLoader.setLineMeters(null);
        uiLoader.setCategoryMeters(null);
        uiLoader.setModelMeters(null);

        uiLoader = null;
    }

    @Test
    public void testLoadComboBox01() {

        // Arrange
        List<String> expectedLineMeterNames = mockLineMeters().stream().map(LineMeterDTO::getName).collect(Collectors.toList());
        ComboBox<String> comboBox = new ComboBox<>();

        // Act
        uiLoader.loadComboBox(comboBox);

        // Assert
        assertEquals("Confirms if the values of ComboBox items are the same as the values they should have", expectedLineMeterNames, comboBox.getItems());
    }

    @Test
    public void testLoadTreeItem01() {
        // Arrange
        TreeItem<String> treeItem = new TreeItem<>();

        // Act
        uiLoader.loadTreeItem(treeItem, "Cronos");

        // Assert
        assertTrue("Confirms if all TreeItem childrens are expanded", treeItem.getChildren().stream().allMatch(TreeItem::isExpanded));
    }

    @Test
    public void testLoadTreeItem02() {
        // Arrange
        TreeItem<String> treeItem = new TreeItem<>();

        // Act
        uiLoader.loadTreeItem(treeItem, "Cronos");

        // Assert
        assertEquals("Confirms if the categories have the expected values", "[TreeItem [ value: Cronos Old ], TreeItem [ value: Cronos L ], TreeItem [ value: Cronos-NG ]]", treeItem.getChildren().toString());
    }

    @Test
    public void testLoadTreeItem03() {
        // Arrange
        TreeItem<String> treeItem = new TreeItem<>();

        // Act
        uiLoader.loadTreeItem(treeItem, "Ares");

        // Assert
        assertEquals("Confirms if the categories have the expected values", "[TreeItem [ value: Ares TB ], TreeItem [ value: Ares THS ]]", treeItem.getChildren().toString());
    }

    @Test
    public void testLoadTreeItem04() {
        // Arrange
        TreeItem<String> treeItem = new TreeItem<>();

        // Act
        uiLoader.loadTreeItem(treeItem, "Cronos");

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: Cronos 6001-A ], TreeItem [ value: Cronos 6003 ], TreeItem [ value: Cronos 7023 ]]", treeItem.getChildren().get(0).getChildren().toString());
    }

    @Test
    public void testLoadTreeItem05() {
        // Arrange
        TreeItem<String> treeItem = new TreeItem<>();

        // Act
        uiLoader.loadTreeItem(treeItem, "Cronos");

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: Cronos 6021L ], TreeItem [ value: Cronos 7023L ]]", treeItem.getChildren().get(1).getChildren().toString());
    }

    @Test
    public void testLoadTreeItem06() {
        // Arrange
        TreeItem<String> treeItem = new TreeItem<>();

        // Act
        uiLoader.loadTreeItem(treeItem, "Cronos");

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: Cronos 6001-NG ], TreeItem [ value: Cronos 6003-NG ], TreeItem [ value: Cronos 6021-NG ], TreeItem [ value: Cronos 6031-NG ], TreeItem [ value: Cronos 7021-NG ], TreeItem [ value: Cronos 7023-NG ]]", treeItem.getChildren().get(2).getChildren().toString());
    }

    @Test
    public void testLoadTreeItem07() {
        // Arrange
        TreeItem<String> treeItem = new TreeItem<>();

        // Act
        uiLoader.loadTreeItem(treeItem, "Ares");

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: ARES 7021 ], TreeItem [ value: ARES 7031 ], TreeItem [ value: ARES 7023 ]]", treeItem.getChildren().get(0).getChildren().toString());
    }

    @Test
    public void testLoadTreeItem08() {
        // Arrange
        TreeItem<String> treeItem = new TreeItem<>();

        // Act
        uiLoader.loadTreeItem(treeItem, "Ares");

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: ARES 8023 15 ], TreeItem [ value: ARES 8023 200 ], TreeItem [ value: ARES 8023 2,5 ]]", treeItem.getChildren().get(1).getChildren().toString());
    }

    // Mocks
    private List<LineMeterDTO> mockLineMeters() {
        List<LineMeterDTO> meterLines = new ArrayList<>();

        meterLines.add(new LineMeterDTO(UUID.fromString("f02896d5-d43c-4ef8-8ee9-b871e4b68944"), "Cronos"));
        meterLines.add(new LineMeterDTO(UUID.fromString("e4d2faaa-870f-40ff-a626-aef515ba66da"), "Ares"));

        return meterLines;
    }

    private List<CategoryMeterDTO> mockCategoryMeters(int index) {
        List<CategoryMeterDTO> allCategories = new ArrayList<>();
        List<CategoryMeterDTO> cronosCategories = new ArrayList<>();
        List<CategoryMeterDTO> aresCategories = new ArrayList<>();

        // Cronos Categories
        cronosCategories.add(new CategoryMeterDTO(UUID.fromString("4c577511-d95b-48bc-8407-8c30e537904c"), "Cronos Old", mockLineMeters().get(0)));
        cronosCategories.add(new CategoryMeterDTO(UUID.fromString("7f465402-d6ac-4991-9757-329380634ecd"), "Cronos L", mockLineMeters().get(0)));
        cronosCategories.add(new CategoryMeterDTO(UUID.fromString("021fd081-4bbc-4232-a0bb-00deb5abfecb"), "Cronos-NG", mockLineMeters().get(0)));

        // Ares Categories
        aresCategories.add(new CategoryMeterDTO(UUID.fromString("729cbffa-0f67-4503-aa6c-4a4a5e7f9b4c"), "Ares TB", mockLineMeters().get(1)));
        aresCategories.add(new CategoryMeterDTO(UUID.fromString("ae396500-6706-4e74-94bd-628594d39ee4"), "Ares THS", mockLineMeters().get(1)));

        allCategories.addAll(cronosCategories);
        allCategories.addAll(aresCategories);

        if (index == 0) {
            return allCategories;
        } else if (index == 1) {
            return cronosCategories;
        } else if (index == 2) {
            return aresCategories;
        } else {
            throw new IllegalArgumentException("Invalid Index");
        }
    }

    private List<ModelMeterDTO> mockModelMeters() {

        List<ModelMeterDTO> allModels = new ArrayList<>();

        // CronosOld Models
        allModels.add(new ModelMeterDTO(UUID.fromString("52ccbce5-098a-4975-ac31-8ea882a2b275"), "Cronos 6001-A", mockCategoryMeters(1).get(0)));
        allModels.add(new ModelMeterDTO(UUID.fromString("9e7c85ce-288a-45b6-8c88-cab867a4656b"), "Cronos 6003", mockCategoryMeters(1).get(0)));
        allModels.add(new ModelMeterDTO(UUID.fromString("dbb4b3a1-cf08-46c2-ada7-fe28f5aa3f38"), "Cronos 7023", mockCategoryMeters(1).get(0)));

        // CronosL Models
        allModels.add(new ModelMeterDTO(UUID.fromString("051477f1-514a-4440-b320-97dc15b39558"), "Cronos 6021L", mockCategoryMeters(1).get(1)));
        allModels.add(new ModelMeterDTO(UUID.fromString("90271375-5e17-4d40-b544-09b0dcb33a3a"), "Cronos 7023L", mockCategoryMeters(1).get(1)));

        // CronosNG Models
        allModels.add(new ModelMeterDTO(UUID.fromString("1d195136-a995-44ee-956c-32ff3d46b258"), "Cronos 6001-NG", mockCategoryMeters(1).get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("ccdd04e3-9f81-42b5-b8c9-fe75f506e856"), "Cronos 6003-NG", mockCategoryMeters(1).get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("b7ae1ee2-bdee-4ef3-a2b5-b7c08bf7e92c"), "Cronos 6021-NG", mockCategoryMeters(1).get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("01509d40-db44-4086-84ea-8b1f4e2916a6"), "Cronos 6031-NG", mockCategoryMeters(1).get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("254d1ac0-f2eb-4a96-ae2c-5cefc113be7f"), "Cronos 7021-NG", mockCategoryMeters(1).get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("bfe939cc-4f9c-4bd5-bf4d-003dd820cc50"), "Cronos 7023-NG", mockCategoryMeters(1).get(2)));

        // AresTB Models
        allModels.add(new ModelMeterDTO(UUID.fromString("d4f4a8f9-e9b7-4aca-9d21-1cfd94b1acb3"), "ARES 7021", mockCategoryMeters(2).get(0)));
        allModels.add(new ModelMeterDTO(UUID.fromString("fe549265-cd16-4a24-a85c-374516ed0ff2"), "ARES 7031", mockCategoryMeters(2).get(0)));
        allModels.add(new ModelMeterDTO(UUID.fromString("998719e8-ada5-4c5e-9dc6-7aba60e0fd8a"), "ARES 7023", mockCategoryMeters(2).get(0)));

        // AresTHS Models
        allModels.add(new ModelMeterDTO(UUID.fromString("e772a2b7-18d4-4f0f-8d6c-022c6d80b953"), "ARES 8023 15", mockCategoryMeters(2).get(1)));
        allModels.add(new ModelMeterDTO(UUID.fromString("ac47df06-5052-4841-a924-49a3ea2b6184"), "ARES 8023 200", mockCategoryMeters(2).get(1)));
        allModels.add(new ModelMeterDTO(UUID.fromString("4dc30e9e-d164-4f15-b961-6466db0d975c"), "ARES 8023 2,5", mockCategoryMeters(2).get(1)));

        return allModels;
    }
}
