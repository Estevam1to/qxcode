package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.Main;
import com.qxcode.Model.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class QuestionComponent {

    @FXML
    private Label numberQuestion;
    @FXML
    private Label nameQuestion;
    @FXML
    private CheckBox favoriteQuestion;
    @FXML
    private Label levelQuestion;

    TelaQuestion telaQuestion;

    CategoryDAO dao;
    private Question question;

    private ControllerQuestion controllerQuestion;

    public QuestionComponent(){
        telaQuestion = new TelaQuestion();
        dao = new CategoryDAO();
        controllerQuestion = new ControllerQuestion();
    }

    public void setQuestion(Question question){
        this.question = question;
        this.setarInfoQuestion();
    }

    private void setarInfoQuestion() {
        boolean favorite;
        if(question.getFavorite() == 1){
            favorite = true;
        }else{
            favorite = false;
        }
        if (question != null) {
            this.numberQuestion.setText(String.valueOf(question.getId()));
            this.nameQuestion.setText(question.getTitle());
            this.favoriteQuestion.setSelected(favorite);
            this.levelQuestion.setText(String.valueOf(question.getDifficulty()));
        } else {
            System.out.println("Erro ao setar info, question is null!");
        }
    }

    public void checkboxAddFavorite() {
        controllerQuestion.updateQuestionFavorite(this.question.getId());
    }

    public void entrarQuestion(MouseEvent mouseEvent) throws IOException {
        Main.setRoot(telaQuestion.getTela(), question.getId());
    }
}
