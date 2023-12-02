package com.qxcode.Controller.TelasController;

import com.qxcode.Controller.ComponentController.CategoryComponent;
import com.qxcode.Controller.ComponentController.IComponentController;
import com.qxcode.Controller.ControllerCategory;
import com.qxcode.Controller.NavBar2Component;
import com.qxcode.Controller.NavBarComponent;
import com.qxcode.Main;
import com.qxcode.Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TelaCategory implements IViewController {
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

    @Override
    @FXML
    public void initialize() throws IOException {
        this.initNavBar();
        this.initGridCategories();

    }


    public void initNavBar() throws IOException {
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
            IComponentController childController = childLoader.getController();
            childController.setModel(categoria);

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

    @Override
    public String getTela() {
        return "View/telaCategory.fxml";
    }
}
