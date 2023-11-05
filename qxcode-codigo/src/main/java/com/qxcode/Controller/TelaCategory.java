package com.qxcode.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;


public class TelaCategory {
    @FXML
    public AnchorPane telaCategory;
    @FXML
    private GridPane gridPane;
    @FXML
    private ArrayList<Pane> categoryCards;
    
    @FXML
    public void initialize() {
        categoryCards = new ArrayList<>();

        ControllerCategory controllerCategory = new ControllerCategory();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerCategory.getTela()));
            Pane cardCategory = loader.load();
            categoryCards.add(cardCategory);
            addCategoryCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCategoryCards() {
        int columnIndex = 0;
        int rowIndex = 0;
    
        for (Pane categoryCard : categoryCards) {
            GridPane.setColumnIndex(categoryCard, columnIndex);
            GridPane.setRowIndex(categoryCard, rowIndex);
    
            gridPane.getChildren().add(categoryCard);

            columnIndex++;
            if (columnIndex >= gridPane.getColumnCount()) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    public String getTela() {
        return "com/qxcode/View/telaCategory.fxml";
    }
}
