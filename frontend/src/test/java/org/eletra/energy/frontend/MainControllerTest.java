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
    public void setUp() {

        mc = spy(new MainController());

        mc.setComboBox(new ComboBox<>());
        mc.setTreeView(new TreeView<>());
        mc.setTreeItem(new TreeItem<>());
        mc.setTitledPaneLinhas(new TitledPane());
        mc.setTitledPaneModelos(new TitledPane());
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
        mc.setLineMeters(null);
        mc.setCategoryMeters(null);
        mc.setModelMeters(null);
        mc = null;
    }

    @Test
    public void testInitialize01() throws IOException {

        // Arrange
        mc.setApiLineMeterService(mock(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(mock(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(mock(ApiModelMeterService.class));

        when(mc.getApiLineMeterService().getLineMeters("meter-lines")).thenReturn(mockLineMeters());
        when(mc.getApiCategoryMeterService().getCategoryMeters("meter-categories")).thenReturn(mockCategoryMeters());
        when(mc.getApiModelMeterService().getModelMeters("meter-models")).thenReturn(mockModelMeters());

        // Act
        mc.initialize();

        // Assert
        verify(mc.getApiLineMeterService(), times(1)).getLineMeters("meter-lines");
        verify(mc.getApiCategoryMeterService(), times(1)).getCategoryMeters("meter-categories");
        verify(mc.getApiModelMeterService(), times(1)).getModelMeters("meter-models");
    }

    @Test
    public void testInitialize02() {

        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        List<String> expectedLineMeterNames = mockLineMeters().stream().map(LineMeterDTO::getName).collect(Collectors.toList());

        // Act
        mc.initialize();

        // Assert
        assertEquals(expectedLineMeterNames, mc.getComboBox().getItems());
    }

    @Test
    public void testInitialize03() {

        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        // Act
        mc.initialize();

        // Assert
        assertNull(mc.getComboBox().getSelectionModel().getSelectedItem());
        assertTrue(mc.getTitledPaneModelos().isDisable());
    }

    @Test
    public void testOnLineSelect01() {
        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        mc.initialize();

        // Act
        mc.getComboBox().getSelectionModel().select((new Random()).nextInt(mc.getComboBox().getItems().size()));
        mc.onLineSelect();

        // Assert
        assertTrue(mc.getTreeItem().isExpanded());
        assertFalse(mc.getTitledPaneModelos().isDisable());
        assertTrue(mc.getTitledPaneModelos().isExpanded());
        assertTrue(mc.getTreeItem().isExpanded());
        assertTrue(mc.getTreeItem().getChildren().stream().allMatch(TreeItem::isExpanded));
    }

    @Test
    public void testOnLineSelect02() {
        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        mc.initialize();

        // Act
        mc.getComboBox().getSelectionModel().select("Cronos");
        mc.onLineSelect();

        // Assert
        assertEquals("[TreeItem [ value: Cronos Old ], TreeItem [ value: Cronos L ], TreeItem [ value: Cronos-NG ]]", mc.getTreeItem().getChildren().toString());
    }

    @Test
    public void testOnLineSelect03() {
        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        mc.initialize();

        // Act
        mc.getComboBox().getSelectionModel().select("Ares");
        mc.onLineSelect();

        // Assert
        assertEquals("[TreeItem [ value: Ares TB ], TreeItem [ value: Ares THS ]]", mc.getTreeItem().getChildren().toString());
    }

    @Test
    public void testOnLineSelect04() {
        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        mc.initialize();

        // Act
        mc.getComboBox().getSelectionModel().select("Cronos");
        mc.onLineSelect();

        // Assert
        assertEquals("[TreeItem [ value: Cronos 6001-A ], TreeItem [ value: Cronos 6003 ], TreeItem [ value: Cronos 7023 ]]", mc.getTreeItem().getChildren().get(0).getChildren().toString());
    }

    @Test
    public void testOnLineSelect05() {
        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        mc.initialize();

        // Act
        mc.getComboBox().getSelectionModel().select("Cronos");
        mc.onLineSelect();

        // Assert
        assertEquals("[TreeItem [ value: Cronos 6021L ], TreeItem [ value: Cronos 7023L ]]", mc.getTreeItem().getChildren().get(1).getChildren().toString());
    }

    @Test
    public void testOnLineSelect06() {
        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        mc.initialize();

        // Act
        mc.getComboBox().getSelectionModel().select("Cronos");
        mc.onLineSelect();

        // Assert
        assertEquals("[TreeItem [ value: Cronos 6001-NG ], TreeItem [ value: Cronos 6003-NG ], TreeItem [ value: Cronos 6021-NG ], TreeItem [ value: Cronos 6031-NG ], TreeItem [ value: Cronos 7021-NG ], TreeItem [ value: Cronos 7023-NG ]]", mc.getTreeItem().getChildren().get(2).getChildren().toString());
    }

    @Test
    public void testOnLineSelect07() {
        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        mc.initialize();

        // Act
        mc.getComboBox().getSelectionModel().select("Ares");
        mc.onLineSelect();

        // Assert
        assertEquals("[TreeItem [ value: ARES 7021 ], TreeItem [ value: ARES 7031 ], TreeItem [ value: ARES 7023 ]]", mc.getTreeItem().getChildren().get(0).getChildren().toString());
    }

    @Test
    public void testOnLineSelect08() {
        // Arrange
        mc.setApiLineMeterService(spy(ApiLineMeterService.class));
        mc.setApiCategoryMeterService(spy(ApiCategoryMeterService.class));
        mc.setApiModelMeterService(spy(ApiModelMeterService.class));

        mc.initialize();

        // Act
        mc.getComboBox().getSelectionModel().select("Ares");
        mc.onLineSelect();

        // Assert
        assertEquals("[TreeItem [ value: ARES 8023 15 ], TreeItem [ value: ARES 8023 200 ], TreeItem [ value: ARES 8023 2,5 ]]", mc.getTreeItem().getChildren().get(1).getChildren().toString());
    }

    // Mocks
    private List<LineMeterDTO> mockLineMeters() {
        List<LineMeterDTO> meterLines = new ArrayList<>();

        meterLines.add(new LineMeterDTO(UUID.fromString("f02896d5-d43c-4ef8-8ee9-b871e4b68944"), "Cronos"));
        meterLines.add(new LineMeterDTO(UUID.fromString("e4d2faaa-870f-40ff-a626-aef515ba66da"), "Ares"));

        return meterLines;
    }

    private List<CategoryMeterDTO> mockCategoryMeters() {
        List<CategoryMeterDTO> allCategories = new ArrayList<>();

        // Cronos Categories
        allCategories.add(new CategoryMeterDTO(UUID.fromString("4c577511-d95b-48bc-8407-8c30e537904c"), "Cronos Old", mockLineMeters().get(0)));
        allCategories.add(new CategoryMeterDTO(UUID.fromString("7f465402-d6ac-4991-9757-329380634ecd"), "Cronos L", mockLineMeters().get(0)));
        allCategories.add(new CategoryMeterDTO(UUID.fromString("021fd081-4bbc-4232-a0bb-00deb5abfecb"), "Cronos-NG", mockLineMeters().get(0)));

        // Ares Categories
        allCategories.add(new CategoryMeterDTO(UUID.fromString("729cbffa-0f67-4503-aa6c-4a4a5e7f9b4c"), "Ares TB", mockLineMeters().get(1)));
        allCategories.add(new CategoryMeterDTO(UUID.fromString("ae396500-6706-4e74-94bd-628594d39ee4"), "Ares THS", mockLineMeters().get(1)));

        return allCategories;
    }

    private List<ModelMeterDTO> mockModelMeters() {

        List<ModelMeterDTO> allModels = new ArrayList<>();

        // CronosOld Models
        allModels.add(new ModelMeterDTO(UUID.fromString("52ccbce5-098a-4975-ac31-8ea882a2b275"), "Cronos 6001-A", mockCategoryMeters().get(0)));
        allModels.add(new ModelMeterDTO(UUID.fromString("9e7c85ce-288a-45b6-8c88-cab867a4656b"), "Cronos 6003", mockCategoryMeters().get(0)));
        allModels.add(new ModelMeterDTO(UUID.fromString("dbb4b3a1-cf08-46c2-ada7-fe28f5aa3f38"), "Cronos 7023", mockCategoryMeters().get(0)));

        // CronosL Models
        allModels.add(new ModelMeterDTO(UUID.fromString("051477f1-514a-4440-b320-97dc15b39558"), "Cronos 6021L", mockCategoryMeters().get(1)));
        allModels.add(new ModelMeterDTO(UUID.fromString("90271375-5e17-4d40-b544-09b0dcb33a3a"), "Cronos 7023L", mockCategoryMeters().get(1)));

        // CronosNG Models
        allModels.add(new ModelMeterDTO(UUID.fromString("1d195136-a995-44ee-956c-32ff3d46b258"), "Cronos 6001-NG", mockCategoryMeters().get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("ccdd04e3-9f81-42b5-b8c9-fe75f506e856"), "Cronos 6003-NG", mockCategoryMeters().get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("b7ae1ee2-bdee-4ef3-a2b5-b7c08bf7e92c"), "Cronos 6021-NG", mockCategoryMeters().get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("01509d40-db44-4086-84ea-8b1f4e2916a6"), "Cronos 6031-NG", mockCategoryMeters().get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("254d1ac0-f2eb-4a96-ae2c-5cefc113be7f"), "Cronos 7021-NG", mockCategoryMeters().get(2)));
        allModels.add(new ModelMeterDTO(UUID.fromString("bfe939cc-4f9c-4bd5-bf4d-003dd820cc50"), "Cronos 7023-NG", mockCategoryMeters().get(2)));

        // AresTB Models
        allModels.add(new ModelMeterDTO(UUID.fromString("d4f4a8f9-e9b7-4aca-9d21-1cfd94b1acb3"), "ARES 7021", mockCategoryMeters().get(3)));
        allModels.add(new ModelMeterDTO(UUID.fromString("fe549265-cd16-4a24-a85c-374516ed0ff2"), "ARES 7031", mockCategoryMeters().get(3)));
        allModels.add(new ModelMeterDTO(UUID.fromString("998719e8-ada5-4c5e-9dc6-7aba60e0fd8a"), "ARES 7023", mockCategoryMeters().get(3)));

        // AresTHS Models
        allModels.add(new ModelMeterDTO(UUID.fromString("e772a2b7-18d4-4f0f-8d6c-022c6d80b953"), "ARES 8023 15", mockCategoryMeters().get(4)));
        allModels.add(new ModelMeterDTO(UUID.fromString("ac47df06-5052-4841-a924-49a3ea2b6184"), "ARES 8023 200", mockCategoryMeters().get(4)));
        allModels.add(new ModelMeterDTO(UUID.fromString("4dc30e9e-d164-4f15-b961-6466db0d975c"), "ARES 8023 2,5", mockCategoryMeters().get(4)));

        return allModels;
    }
}