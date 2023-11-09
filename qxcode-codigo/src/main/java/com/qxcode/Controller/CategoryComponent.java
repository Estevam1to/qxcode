package com.qxcode.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CategoryComponent {

    TelaListQuestion telaListQuestion = new TelaListQuestion();

    @FXML
    private Label LabelNameCategory;

    public void entrarListQuestion(MouseEvent mouseEvent) {
        telaListQuestion.getTela(LabelNameCategory.getText());
    }
}
