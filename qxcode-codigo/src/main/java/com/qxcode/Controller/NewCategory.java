package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NewCategory {

    @FXML
    private TextArea decriptionInput;

    @FXML
    private TextArea titleInput;

    private CategoryDAO categoryDAO;

    public NewCategory() {
        this.categoryDAO = new CategoryDAO();
    }

    @FXML
    private void salvarCategoria() {
        String titulo = titleInput.getText();
        String descricao = decriptionInput.getText();

        categoryDAO.insertCategory(titulo, descricao);

        // Limpar os campos após a adição da categoria
        titleInput.clear();
        decriptionInput.clear();

        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
    }

    public void entrarInicio(MouseEvent mouseEvent) throws IOException {
        Main.setRoot("View/telaCategory.fxml");
    }

    public String getTela() {
        return "/com/qxcode/View/telaNewCategory.fxml";
    }

}
