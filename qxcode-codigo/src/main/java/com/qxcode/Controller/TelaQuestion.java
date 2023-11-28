package com.qxcode.Controller;

import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Main;
import com.qxcode.Model.Question;
import com.qxcode.Utils.TranformaEmArquivo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

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
    @FXML
    private Pane navBar;

    QuestionDAO dao;

    private int questionId;

    public TelaQuestion(){
        dao = new QuestionDAO();
    }

    public void setId(int id){
        this.questionId = id;
    }


    @FXML
    public void initialize() throws IOException {
        btnSubmeter.setOnAction(e -> submeterAcao());
        this.initNavBar();
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
    private void initNavBar() throws IOException {
        FXMLLoader childLoader = obterFXMLNavBarLoader();
        AnchorPane childNode = childLoader.load();
        NavBarComponent childController = childLoader.getController();
        navBar.getChildren().add(childNode);
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


    public String getTela() {
        return "View/telaQuestion.fxml";
    }

}
