package com.qxcode.Controller;

import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Model.Question;
import com.qxcode.Utils.TranformaEmArquivo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class TelaQuestion {
    TranformaEmArquivo tranformaEmArquivo;
    @FXML
    private TextArea entradaUsuario;
    @FXML
    private Button btnSubmeter;
    @FXML
    private Text questionDescription;
    @FXML
    private Text questionExInput;
    @FXML
    private Label questionName;

    QuestionDAO dao;

    private int questionId;

    public TelaQuestion(){
        dao = new QuestionDAO();
    }

    public void setId(int id){
        this.questionId = id;
    }


    @FXML
    public void initialize() {
        btnSubmeter.setOnAction(e -> submeterAcao());
        initQuestion();
    }

    private void submeterAcao() {
        String entrada = entradaUsuario.getText();
        tranformaEmArquivo = new TranformaEmArquivo(entrada);
        tranformaEmArquivo.solver();
    }

    public void initQuestion(){
        Question question = dao.getQuestionById(questionId);
        questionName.setText(question.getTitle());
        questionDescription.setText(question.getDescription());
    }





    public String getTela() {
        return "View/telaQuestion.fxml";
    }

}
