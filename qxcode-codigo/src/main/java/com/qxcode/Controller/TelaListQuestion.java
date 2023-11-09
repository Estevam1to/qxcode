package com.qxcode.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;


public class TelaListQuestion {
    @FXML
    public AnchorPane telaListQuestion;
    @FXML
    private GridPane gridPane;
    @FXML
    private ArrayList<Pane> questionCards;

    @FXML
    public void initialize() {
        questionCards = new ArrayList<>();

        ControllerQuestion controllerQuestion = new ControllerQuestion();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerQuestion.getTela()));
            Pane cardQuestion = loader.load();
            questionCards.add(cardQuestion);
            addQuestionCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addQuestionCards() {
        int columnIndex = 0;
        int rowIndex = 0;

        for (Pane questionCard : questionCards) {
            GridPane.setColumnIndex(questionCard, columnIndex);
            GridPane.setRowIndex(questionCard, rowIndex);

            gridPane.getChildren().add(questionCard);

            columnIndex++;
            if (columnIndex >= gridPane.getColumnCount()) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    public String getTela(String nameCategory) {
        System.out.println(nameCategory);
        return "com/qxcode/View/telaListQuestion.fxml";
    }
}
