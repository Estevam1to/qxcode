package com.qxcode.Controller.ComponentController;

import com.qxcode.Controller.ControllerQuestion;
import com.qxcode.Controller.TelasController.IViewController;
import com.qxcode.Controller.TelasController.TelaQuestion;
import com.qxcode.DAO.CategoryDAO;
import com.qxcode.Main;
import com.qxcode.Model.Category;
import com.qxcode.Model.Question;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class QuestionComponent implements IComponentController{

    @FXML
    private Label numberQuestion;
    @FXML
    private Label nameQuestion;
    @FXML
    private CheckBox favoriteQuestion;
    @FXML
    private Label levelQuestion;

    IViewController telaQuestion;

    CategoryDAO dao;
    private Question question;

    private ControllerQuestion controllerQuestion;

    public QuestionComponent(){
        telaQuestion = new TelaQuestion();
        dao = new CategoryDAO();
        controllerQuestion = new ControllerQuestion();
    }

    public <T> void setModel(T question){
        this.question = (Question) question;
        this.setInfo();
    }

    public void setInfo() {
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

    public void entrarDetalhes(MouseEvent mouseEvent) throws IOException {
        Main.setRoot(telaQuestion.getTela(), question.getId());
    }
}
