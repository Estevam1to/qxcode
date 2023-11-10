package com.qxcode.Controller;

import com.qxcode.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CategoryComponent {

    TelaListQuestion telaListQuestion = new TelaListQuestion();

    @FXML
    private Label LabelNameCategory;

    public void entrarListQuestion(MouseEvent mouseEvent) throws IOException {
        Main.setRoot(telaListQuestion.getTela(LabelNameCategory.getText()));
    }
}
