package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.JDBC.JDBC;
import com.qxcode.Main;
import com.qxcode.Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class TelaCategory {
    @FXML
    public AnchorPane telaCategory;
    @FXML
    private GridPane gridPane;
    @FXML
    private ArrayList<Pane> categoryCards;

    @FXML
    private Pane navBar;
    @FXML
    private Pane navBar2;
    @FXML
    private Rectangle teste;
    @FXML
    private ScrollPane scrollPane;


    @FXML
    public void initialize() throws IOException {
        this.initNavBar();
        this.initGridCategories();

    }


    private void initNavBar() throws IOException {
        FXMLLoader childLoader = obterFXMLNavBarLoader();
        AnchorPane childNode = childLoader.load();
        NavBarComponent childController = childLoader.getController();
        navBar.getChildren().add(childNode);

        FXMLLoader childLoader2 = obterFXMLNavBar2Loader();
        AnchorPane childNode2 = childLoader2.load();
        NavBar2Component childController2 = childLoader2.getController();
        navBar2.getChildren().add(childNode2);
    }


    private void initGridCategories(){
        List<Category> categories = getAllCategories();

        for (Category categoria : categories) {
            if (categoria != null) {
                this.adicionarCategoryEmGrid(categoria);
            } else {
                // handle the null case, e.g. log a warning
                System.out.println("Warning: null Category object encountered");
            }
        }

    }


    private void adicionarCategoryEmGrid(Category categoria) {
        try {
            FXMLLoader childLoader = obterFXMLCategoryLoader();
            AnchorPane childNode = childLoader.load();
            CategoryComponent childController = childLoader.getController();
            childController.setCategory(categoria);

            // Adiciona o categoryCard ao gridPane
            gridPane.getChildren().add(childNode);

            // Calcula as posições da coluna e da linha
            int columnIndex = gridPane.getChildren().indexOf(childNode) % gridPane.getColumnCount();
            int rowIndex = gridPane.getChildren().indexOf(childNode) / gridPane.getColumnCount();

            // Define as posições na grade
            GridPane.setColumnIndex(childNode, columnIndex);
            GridPane.setRowIndex(childNode, rowIndex);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private FXMLLoader obterFXMLCategoryLoader() {
        URL resource = Main.class.getResource("View/components/categoryComponent.fxml");
        if (resource == null) {
            System.out.println("FXML file not found");
        } else {
            System.out.println("FXML file found at: " + resource);
        }
        return new FXMLLoader(resource);
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


    private List<Category> getAllCategories() {
        ControllerCategory controller = new ControllerCategory();
        List<Category> categories = controller.getAllCategories();
        if (categories != null) {
            return categories;
        } else {
            // handle the null case, e.g. return an empty list
            return new ArrayList<>();
        }
    }

    public String getTela() {
        return "View/telaCategory.fxml";
    }
}
