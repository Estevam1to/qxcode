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

public class NewCategory implements IViewController{

    @FXML
    private TextArea decriptionInput;

    @FXML
    private TextArea titleInput;

    @FXML
    private Pane navBar;

    @FXML
    private Pane navBar2;
    private CategoryDAO categoryDAO;


    public NewCategory(){
        this.categoryDAO = new CategoryDAO();
    }

    @Override
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

    public void initNavBar() throws IOException {
        FXMLLoader childLoader = obterFXMLNavBarLoader();
        AnchorPane childNode = childLoader.load();
        navBar.getChildren().add(childNode);

        FXMLLoader childLoader2 = obterFXMLNavBar2Loader();
        AnchorPane childNode2 = childLoader2.load();
        NavBar2Component childController2 = childLoader2.getController();
        navBar2.getChildren().add(childNode2);
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

    private FXMLLoader obterFXMLNavBar2Loader() {
        URL resource = Main.class.getResource("View/components/navBar2.fxml");
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
