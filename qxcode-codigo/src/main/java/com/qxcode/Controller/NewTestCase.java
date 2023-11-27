package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Main;
import com.qxcode.Model.Category;
import com.qxcode.Model.Question;
import com.qxcode.Controller.ControllerQuestionDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewTestCase implements Initializable{

    @FXML
    private TextArea inputs;
    @FXML
    private TextArea outputs;
    @FXML
    private ChoiceBox<String> questionInput;

    private ControllerTestCaseDAO ControllerTestCaseDAO;
    private ControllerQuestionDAO ControllerQuestionDAO;

    public NewTestCase() {
        this.ControllerTestCaseDAO = new ControllerTestCaseDAO();
        this.ControllerQuestionDAO = new ControllerQuestionDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadQuestionInput();
    }

    private void loadQuestionInput() {
        List<Question> questions = ControllerQuestionDAO.getAllQuestions();
        for (Question question : questions)  {
            String title = question.getTitle();
            questionInput.getItems().add(title);
        }
    }

    public int getQuestion(String title){
        Question question = ControllerQuestionDAO.getQuestionByTitle(title);
        return question.getId();
    }

    @FXML
    private void saveTestCase() {
        String input = inputs.getText();
        String output = outputs.getText();
        int questionId = this.getQuestion(questionInput.getValue());

        ControllerTestCaseDAO.insert(input, output, questionId);

        inputs.clear();
        outputs.clear();


        System.out.println("inputs: " + input);
        System.out.println("outputs: " + output);
        System.out.println("id da questao:" + questionId);
    }

    public void entrarInicio(MouseEvent mouseEvent) throws IOException {
        Main.setRoot("View/telaCategory.fxml");
    }

    public String getTela() {
        return "/com/qxcode/View/telaNewTestCase.fxml";
    }
}
