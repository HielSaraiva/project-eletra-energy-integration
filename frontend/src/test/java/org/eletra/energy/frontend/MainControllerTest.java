package org.eletra.energy.frontend;


import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.eletra.energy.frontend.controllers.MainController;
import org.eletra.energy.frontend.models.dtos.CategoryMeterDTO;
import org.eletra.energy.frontend.models.dtos.LineMeterDTO;
import org.eletra.energy.frontend.models.dtos.ModelMeterDTO;
import org.eletra.energy.frontend.services.ApiCategoryMeterService;
import org.eletra.energy.frontend.services.ApiLineMeterService;
import org.eletra.energy.frontend.services.ApiModelMeterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.testfx.framework.junit.ApplicationTest;


import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MainControllerTest extends ApplicationTest {

    private MainController mc;

    @Before
    public void setUp() throws IOException {

        mc = spy(new MainController());

        mc.setComboBox(new ComboBox<>());
        mc.setTreeView(new TreeView<>());
        mc.setTreeItem(new TreeItem<>());
        mc.setTitledPaneLinhas(new TitledPane());
        mc.setTitledPaneModelos(new TitledPane());

        mc.setApiLineMeterService(mock(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(mock(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(mock(ApiModelMeterService.class));

        when(mc.getApiLineMeterService().getLineMeters("meter-lines")).thenReturn(mockLineMeters());
        when(mc.getApiCategoryMeterService().getCategoryMeters("meter-categories")).thenReturn(mockCategoryMeters(0));
        when(mc.getApiModelMeterService().getModelMeters("meter-models")).thenReturn(mockModelMeters(0));
    }

    @After
    public void setDown() {

        mc.setComboBox(null);
        mc.setTreeView(null);
        mc.setTreeItem(null);
        mc.setTitledPaneLinhas(null);
        mc.setTitledPaneModelos(null);
        mc.setApiLineMeterService(null);
        mc.setApiCategoryMeterService(null);
        mc.setApiModelMeterService(null);
        mc.setModelMeters(null);
        mc.setCategoryMeters(null);
        mc.setLineMeters(null);

        mc = null;
    }

    @Test
    public void testInitialize01() throws IOException {

        // Arrange
        // No additional arrangement needed as setUp() already mocks the services

        // Act
        mc.initialize();

        // Assert
        verify(mc.getApiLineMeterService(), times(1)).getLineMeters("meter-lines");
        verify(mc.getApiCategoryMeterService(), times(1)).getCategoryMeters("meter-categories");
        verify(mc.getApiModelMeterService(), times(1)).getModelMeters("meter-models");
    }

    @Test
    public void testInitialize02() throws IOException {

        // Arrange
        // No additional arrangement needed as setUp() already mocks the services

        // Act
        mc.initialize();

        // Assert
        assertNull("Confirms if the value of SelectedItem in ComboBox is null", mc.getComboBox().getSelectionModel().getSelectedItem());
        assertTrue("Confirms if TitledPaneModelos is disable", mc.getTitledPaneModelos().isDisable());
    }

    @Test(expected = RuntimeException.class)
    public void testInitialize03() throws IOException {
        // Arrange
        when(mc.getApiLineMeterService().getLineMeters("meter-lines"))
                .thenThrow(new RuntimeException("line service error"));
        when(mc.getApiCategoryMeterService().getCategoryMeters("meter-categories"))
                .thenThrow(new RuntimeException("category service error"));
        when(mc.getApiModelMeterService().getModelMeters("meter-models"))
                .thenThrow(new RuntimeException("model service error"));

        // Act
        mc.initialize();
    }

    @Test
    public void testLoadComboBox() throws IOException {

        // Arrange
        List<String> expectedLineMeterNames = mockLineMeters().stream().map(LineMeterDTO::getName).collect(Collectors.toList());

        // Act
        mc.initialize();

        // Assert
        assertEquals("Confirms if the values of ComboBox items are the same as the values they should have", expectedLineMeterNames, mc.getComboBox().getItems());
    }

    @Test
    public void testOnLineSelect01() throws IOException {
        // Arrange
        mc.initialize();
        mc.setLineMeters(initializeLineMetersDependency(mc));

        // Act
        mc.getComboBox().getSelectionModel().select((new Random()).nextInt(mc.getComboBox().getItems().size()));
        mc.onLineSelect();

        // Assert
        assertFalse("Confirms if TitledPaneModelos is enable", mc.getTitledPaneModelos().isDisable());
        assertTrue("Confirms if TitledPaneModelos is expanded", mc.getTitledPaneModelos().isExpanded());
        assertTrue("Confirms if TreeItem is expanded", mc.getTreeItem().isExpanded());
    }

    @Test(expected = Exception.class)
    public void testOnLineSelect02() throws IOException {
        // Arrange
        mc.initialize();
        mc.getComboBox().getSelectionModel().select(null);

        doThrow(Exception.class).when(mc).onLineSelect();

        // Act
        mc.onLineSelect();
    }

    @Test
    public void testLoadTreeItem01() throws IOException {
        // Arrange
        mc.initialize();
        mc.setLineMeters(initializeLineMetersDependency(mc));

        // Act
        mc.getComboBox().getSelectionModel().select((new Random()).nextInt(mc.getComboBox().getItems().size()));
        mc.onLineSelect();

        // Assert
        assertTrue("Confirms if all TreeItem childrens are expanded", mc.getTreeItem().getChildren().stream().allMatch(TreeItem::isExpanded));
    }

    @Test
    public void testLoadTreeItem02() throws IOException {
        // Arrange
        mc.initialize();
        mc.setLineMeters(initializeLineMetersDependency(mc));

        // Act
        mc.getComboBox().getSelectionModel().select("Cronos");
        mc.onLineSelect();

        // Assert
        assertEquals("Confirms if the categories have the expected values", "[TreeItem [ value: Cronos Old ], TreeItem [ value: Cronos L ], TreeItem [ value: Cronos-NG ]]", mc.getTreeItem().getChildren().toString());
    }

    @Test
    public void testLoadTreeItem03() throws IOException {
        // Arrange
        mc.initialize();
        mc.setLineMeters(initializeLineMetersDependency(mc));

        // Act
        mc.getComboBox().getSelectionModel().select("Ares");
        mc.onLineSelect();

        // Assert
        assertEquals("Confirms if the categories have the expected values", "[TreeItem [ value: Ares TB ], TreeItem [ value: Ares THS ]]", mc.getTreeItem().getChildren().toString());
    }

    @Test
    public void testLoadTreeItem04() throws IOException {
        // Arrange
        mc.initialize();
        mc.setLineMeters(initializeLineMetersDependency(mc));

        // Act
        mc.getComboBox().getSelectionModel().select("Cronos");
        mc.onLineSelect();

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: Cronos 6001-A ], TreeItem [ value: Cronos 6003 ], TreeItem [ value: Cronos 7023 ]]", mc.getTreeItem().getChildren().get(0).getChildren().toString());
    }

    @Test
    public void testLoadTreeItem05() throws IOException {
        // Arrange
        mc.initialize();
        mc.setLineMeters(initializeLineMetersDependency(mc));

        // Act
        mc.getComboBox().getSelectionModel().select("Cronos");
        mc.onLineSelect();

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: Cronos 6021L ], TreeItem [ value: Cronos 7023L ]]", mc.getTreeItem().getChildren().get(1).getChildren().toString());
    }

    @Test
    public void testLoadTreeItem06() throws IOException {
        // Arrange
        mc.initialize();
        mc.setLineMeters(initializeLineMetersDependency(mc));

        // Act
        mc.getComboBox().getSelectionModel().select("Cronos");
        mc.onLineSelect();

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: Cronos 6001-NG ], TreeItem [ value: Cronos 6003-NG ], TreeItem [ value: Cronos 6021-NG ], TreeItem [ value: Cronos 6031-NG ], TreeItem [ value: Cronos 7021-NG ], TreeItem [ value: Cronos 7023-NG ]]", mc.getTreeItem().getChildren().get(2).getChildren().toString());
    }

    @Test
    public void testLoadTreeItem07() throws IOException {
        // Arrange
        mc.initialize();
        mc.setLineMeters(initializeLineMetersDependency(mc));

        // Act
        mc.getComboBox().getSelectionModel().select("Ares");
        mc.onLineSelect();

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: ARES 7021 ], TreeItem [ value: ARES 7031 ], TreeItem [ value: ARES 7023 ]]", mc.getTreeItem().getChildren().get(0).getChildren().toString());
    }

    @Test
    public void testLoadTreeItem08() throws IOException {
        // Arrange
        mc.initialize();
        mc.setLineMeters(initializeLineMetersDependency(mc));

        // Act
        mc.getComboBox().getSelectionModel().select("Ares");
        mc.onLineSelect();

        // Assert
        assertEquals("Confirms if the models have the expected values", "[TreeItem [ value: ARES 8023 15 ], TreeItem [ value: ARES 8023 200 ], TreeItem [ value: ARES 8023 2,5 ]]", mc.getTreeItem().getChildren().get(1).getChildren().toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLoadTreeItem09() throws IOException {
        // Arrange
        mc.initialize();
        mc.getComboBox().setValue("Invalid Line Name");

        doThrow(new IllegalArgumentException()).when(mc).onLineSelect();

        // Act
        mc.onLineSelect();
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

    private List<ModelMeterDTO> mockModelMeters(int index) {

        List<ModelMeterDTO> allModels = new ArrayList<>();
        List<ModelMeterDTO> cronosOldModels = new ArrayList<>();
        List<ModelMeterDTO> cronosLModels = new ArrayList<>();
        List<ModelMeterDTO> cronosNGModels = new ArrayList<>();
        List<ModelMeterDTO> aresTBModels = new ArrayList<>();
        List<ModelMeterDTO> aresTHSModels = new ArrayList<>();

        // CronosOld Models
        cronosOldModels.add(new ModelMeterDTO(UUID.fromString("52ccbce5-098a-4975-ac31-8ea882a2b275"), "Cronos 6001-A", mockCategoryMeters(1).get(0)));
        cronosOldModels.add(new ModelMeterDTO(UUID.fromString("9e7c85ce-288a-45b6-8c88-cab867a4656b"), "Cronos 6003", mockCategoryMeters(1).get(0)));
        cronosOldModels.add(new ModelMeterDTO(UUID.fromString("dbb4b3a1-cf08-46c2-ada7-fe28f5aa3f38"), "Cronos 7023", mockCategoryMeters(1).get(0)));

        // CronosL Models
        cronosLModels.add(new ModelMeterDTO(UUID.fromString("051477f1-514a-4440-b320-97dc15b39558"), "Cronos 6021L", mockCategoryMeters(1).get(1)));
        cronosLModels.add(new ModelMeterDTO(UUID.fromString("90271375-5e17-4d40-b544-09b0dcb33a3a"), "Cronos 7023L", mockCategoryMeters(1).get(1)));

        // CronosNG Models
        cronosNGModels.add(new ModelMeterDTO(UUID.fromString("1d195136-a995-44ee-956c-32ff3d46b258"), "Cronos 6001-NG", mockCategoryMeters(1).get(2)));
        cronosNGModels.add(new ModelMeterDTO(UUID.fromString("ccdd04e3-9f81-42b5-b8c9-fe75f506e856"), "Cronos 6003-NG", mockCategoryMeters(1).get(2)));
        cronosNGModels.add(new ModelMeterDTO(UUID.fromString("b7ae1ee2-bdee-4ef3-a2b5-b7c08bf7e92c"), "Cronos 6021-NG", mockCategoryMeters(1).get(2)));
        cronosNGModels.add(new ModelMeterDTO(UUID.fromString("01509d40-db44-4086-84ea-8b1f4e2916a6"), "Cronos 6031-NG", mockCategoryMeters(1).get(2)));
        cronosNGModels.add(new ModelMeterDTO(UUID.fromString("254d1ac0-f2eb-4a96-ae2c-5cefc113be7f"), "Cronos 7021-NG", mockCategoryMeters(1).get(2)));
        cronosNGModels.add(new ModelMeterDTO(UUID.fromString("bfe939cc-4f9c-4bd5-bf4d-003dd820cc50"), "Cronos 7023-NG", mockCategoryMeters(1).get(2)));

        // AresTB Models
        aresTBModels.add(new ModelMeterDTO(UUID.fromString("d4f4a8f9-e9b7-4aca-9d21-1cfd94b1acb3"), "ARES 7021", mockCategoryMeters(2).get(0)));
        aresTBModels.add(new ModelMeterDTO(UUID.fromString("fe549265-cd16-4a24-a85c-374516ed0ff2"), "ARES 7031", mockCategoryMeters(2).get(0)));
        aresTBModels.add(new ModelMeterDTO(UUID.fromString("998719e8-ada5-4c5e-9dc6-7aba60e0fd8a"), "ARES 7023", mockCategoryMeters(2).get(0)));

        // AresTHS Models
        aresTHSModels.add(new ModelMeterDTO(UUID.fromString("e772a2b7-18d4-4f0f-8d6c-022c6d80b953"), "ARES 8023 15", mockCategoryMeters(2).get(1)));
        aresTHSModels.add(new ModelMeterDTO(UUID.fromString("ac47df06-5052-4841-a924-49a3ea2b6184"), "ARES 8023 200", mockCategoryMeters(2).get(1)));
        aresTHSModels.add(new ModelMeterDTO(UUID.fromString("4dc30e9e-d164-4f15-b961-6466db0d975c"), "ARES 8023 2,5", mockCategoryMeters(2).get(1)));

        allModels.addAll(cronosOldModels);
        allModels.addAll(cronosLModels);
        allModels.addAll(cronosNGModels);
        allModels.addAll(aresTBModels);
        allModels.addAll(aresTHSModels);

        if (index == 0) {
            return allModels;
        } else if (index == 1) {
            return cronosOldModels;
        } else if (index == 2) {
            return cronosLModels;
        } else if (index == 3) {
            return cronosNGModels;
        } else if (index == 4) {
            return aresTBModels;
        } else if (index == 5) {
            return aresTHSModels;
        } else {
            throw new IllegalArgumentException("Invalid Index");
        }
    }

    private List<LineMeterDTO> initializeLineMetersDependency(MainController mc) {
        for (int counter = 0; counter < mc.getLineMeters().size(); counter++) {
            mc.getLineMeters().get(counter).setMeterCategories(mockCategoryMeters(counter + 1));
        }

        for (LineMeterDTO lineMeterDTO : mc.getLineMeters()) {
            if (lineMeterDTO.getName().equals("Cronos")) {
                for (int counter = 0; counter < lineMeterDTO.getMeterCategories().size(); counter++) {
                    lineMeterDTO.getMeterCategories().get(counter).setMeterModels(mockModelMeters(counter + 1));
                }
            } else if (lineMeterDTO.getName().equals("Ares")) {
                for (int counter = 0; counter < lineMeterDTO.getMeterCategories().size(); counter++) {
                    lineMeterDTO.getMeterCategories().get(counter).setMeterModels(mockModelMeters(counter + 4));
                }
            } else {
                throw new IllegalArgumentException("Unknown Line Meter Name: " + lineMeterDTO.getName());
            }
        }

        return mc.getLineMeters();
    }
}