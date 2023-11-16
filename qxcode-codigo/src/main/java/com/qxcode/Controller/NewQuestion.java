package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.DAO.QuestionDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class NewQuestion {

    @FXML
    private TextArea decriptionInput;
    @FXML
    private TextArea titleInput;
    @FXML
    private TextArea dificultyInput;
    @FXML
    private TextArea examplesInput;

    private QuestionDAO questionDAO;

    public NewQuestion() {
        this.questionDAO = new QuestionDAO();
    }

    @FXML
    private void salvarQuestao() {
        String titulo = titleInput.getText();
        String descricao = decriptionInput.getText();
        Integer dificuldade = Integer.parseInt(dificultyInput.getText());
        String exemplos = examplesInput.getText();

        questionDAO.addQuestion(titulo, descricao, dificuldade, exemplos);

        // Limpar os campos após a adição da categoria
        titleInput.clear();
        decriptionInput.clear();
        dificultyInput.clear();
        examplesInput.clear();

        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Dificuldade: " + dificuldade);
        System.out.println("Exemplos: " + exemplos);
    }

    public String getTela() {
        return "/com/qxcode/View/telaNewQuestion.fxml";
    }

}
