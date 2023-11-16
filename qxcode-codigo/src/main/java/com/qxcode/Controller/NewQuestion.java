package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NewQuestion {

    @FXML
    private TextArea decriptionInput;
    @FXML
    private TextArea titleInput;
    @FXML
    private TextArea dificultyInput;
    @FXML
    private TextArea examplesInput;
    @FXML
    private TextArea categoryInput;

    private QuestionDAO questionDAO;

    public NewQuestion() {
        this.questionDAO = new QuestionDAO();
    }

    @FXML
    private void salvarQuestao() {
        String titulo = titleInput.getText();
        String descricao = decriptionInput.getText();
        int dificuldade = Integer.parseInt(dificultyInput.getText());
        String exemplos = examplesInput.getText();
        int id_categoria = Integer.parseInt(categoryInput.getText());


        questionDAO.insertQuestion(titulo, descricao, dificuldade, exemplos, id_categoria);

        // Limpar os campos após a adição da categoria
        titleInput.clear();
        decriptionInput.clear();
        dificultyInput.clear();
        examplesInput.clear();
        categoryInput.clear();


        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Dificuldade: " + dificuldade);
        System.out.println("Exemplos: " + exemplos);
        System.out.println("Categoria: " + id_categoria);

    }

    public void entrarInicio(MouseEvent mouseEvent) throws IOException {
        Main.setRoot("View/telaCategory.fxml");
    }

    public String getTela() {
        return "/com/qxcode/View/telaNewQuestion.fxml";
    }

}
