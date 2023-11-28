package com.qxcode.Controller;


import com.qxcode.Main;
import com.qxcode.Model.Category;
import com.qxcode.Model.Question;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class TelaListQuestion {

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

    private void initNavBar() throws IOException {
        FXMLLoader childLoader = obterFXMLNavBarLoader();
        AnchorPane childNode = childLoader.load();
        NavBarComponent childController = childLoader.getController();
        navBar.getChildren().add(childNode);
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
            QuestionComponent childController = childLoader.getController();
            childController.setQuestion(question);

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


    private List<Question> getAllQuestions() {
        ControllerQuestion controller = new ControllerQuestion();
        List<Question> questions = controller.getQuestionByCategory(this.idCategorySelect);
        if (questions != null) {
            return questions;
        }
        return new ArrayList<>();
    }

    public String getTela() {
        return "View/telaListQuestion.fxml";
    }
}
