package com.qxcode.Controller;

import com.qxcode.Main;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class NavBarComponent {
    TelaCategory telaCategory;

    TelaListQuestion telaListQuestion;
    @FXML
    NewCategory newCategory;
    @FXML
    NewQuestion newQuestion;

    public NavBarComponent(){
        newQuestion = new NewQuestion();
        telaCategory = new TelaCategory();
        telaListQuestion = new TelaListQuestion();
        newCategory = new NewCategory();
    }

    public void entrarInicio(MouseEvent mouseEvent) throws IOException {
        Main.setRoot(telaCategory.getTela());
    }
    public void entrarFavoritos(MouseEvent mouseEvent) throws IOException {
        Main.setRoot(telaListQuestion.getTela(), -1, "Favoritos");
    }
    public void entrarNovaCategoria(MouseEvent mouseEvent) throws IOException {
        Main.setRoot(newCategory.getTela());
    }

    public void entrarNovaQuestao(MouseEvent mouseEvent) throws IOException {
        Main.setRoot(newQuestion.getTela());
    }
}
