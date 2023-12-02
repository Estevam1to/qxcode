package com.qxcode.Controller.TelasController;


import com.qxcode.Controller.ComponentController.IComponentController;
import com.qxcode.Controller.ControllerQuestion;
import com.qxcode.Controller.NavBar2Component;
import com.qxcode.Controller.NavBarComponent;
import com.qxcode.Controller.ComponentController.QuestionComponent;
import com.qxcode.Main;
import com.qxcode.Model.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TelaListQuestion implements IViewController {

    @FXML
    public AnchorPane telaListQuestion;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label nameCategory;

    private String nameCategorySelect;

    private int idCategorySelect;

    private int currentRowIndex = 0;

    @FXML
    private Pane navBar;
    @FXML
    private Pane navBar2;

    @Override
    @FXML
    public void initialize() throws IOException {
        this.initNavBar();
        this.initGridQuestions();

    }

    public void setCategory(String category) {
        this.nameCategorySelect = category;
    }

    public void setId(int id) {
        this.idCategorySelect = id;
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

    private void initGridQuestions() {
        List<Question> questions = getAllQuestions();
        this.nameCategory.setText(nameCategorySelect);

        for (Question question : questions) {
            if (question != null) {
                this.adicionarQuestionEmGrid(question);
            } else {
                // handle the null case, e.g. log a warning
                System.out.println("Warning: null Question object encountered");
            }
        }
    }

    private void adicionarQuestionEmGrid(Question question) {
        try {
            FXMLLoader childLoader = obterFXMLQuestionLoader();
            AnchorPane childNode = childLoader.load();
            IComponentController childController = childLoader.getController();
            childController.setModel(question);

            gridPane.getChildren().add(childNode);

            GridPane.setColumnIndex(childNode, 0);
            int rowIndex = gridPane.getChildren().indexOf(childNode) / gridPane.getColumnCount();


            GridPane.setRowIndex(childNode, rowIndex);
            currentRowIndex++;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private FXMLLoader obterFXMLQuestionLoader() {
        URL resource = Main.class.getResource("View/components/questionComponent.fxml");
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


    private List<Question> getAllQuestions() {
        ControllerQuestion controllerQuestion = new ControllerQuestion();
        List<Question> questions;
        if(this.idCategorySelect == -1){
            questions = controllerQuestion.getFavoriteQuestions();
        }else{
            questions = controllerQuestion.getQuestionByCategory(this.idCategorySelect);
        }

        if (questions != null) {
            return questions;
        }
        return new ArrayList<>();
    }

    @Override
    public String getTela() {
        return "View/telaListQuestion.fxml";
    }
}
