package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

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

        categoryDAO.addCategory(titulo, descricao);

        // Limpar os campos após a adição da categoria
        titleInput.clear();
        decriptionInput.clear();

        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
    }

    public String getTela() {
        return "/com/qxcode/View/telaNewCategory.fxml";
    }

}
