package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class NewCategory {

    @FXML
    private TextArea decriptionInput;

    @FXML
    private TextArea titleInput;

    @FXML
    private Pane navBar;
    private CategoryDAO categoryDAO;


    public NewCategory(){
        this.categoryDAO = new CategoryDAO();
    }

    public void initialize() throws IOException{
        this.initNavBar();
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

    private void initNavBar() throws IOException {
        FXMLLoader childLoader = obterFXMLNavBarLoader();
        AnchorPane childNode = childLoader.load();
        navBar.getChildren().add(childNode);
    }


    private FXMLLoader obterFXMLNavBarLoader() {
        URL resource = Main.class.getResource("View/components/navBar.fxml");
        if (resource == null) {
            System.out.println("FXML file not found");
        } else {
            System.out.println("FXML file found at: " + resource);
        }
        return new FXMLLoader(resource);
    }

    public String getTela() {
        return "/com/qxcode/View/telaNewCategory.fxml";
    }

}
