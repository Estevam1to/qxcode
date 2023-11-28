package com.qxcode.Controller;

import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Model.Question;
import com.qxcode.Utils.TranformaEmArquivo;
import com.qxcode.Utils.FactoryJudge;
import com.qxcode.Utils.WriteInputOutputInFile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.text.Text;
import javafx.scene.control.MenuItem;

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

    private final String AC_RESULT = "AC";
    private final String WA_RESULT = "WA";
    private final String TLE_RESULT = "TLE";
    private final String RE_RESULT = "ERROR";


    public TelaQuestion(){
        dao = new QuestionDAO();
    }

    public void setId(int id){
        this.questionId = id;
    }

    @FXML
    private SplitMenuButton btnLinguagem;

    private FactoryJudge factoryJudge = new FactoryJudge();

    private void addLanguage() {
        btnLinguagem.getItems().add(new MenuItem("Python"));
        btnLinguagem.getItems().add(new MenuItem("C++"));
        btnLinguagem.getItems().add(new MenuItem("Java"));
        btnLinguagem.getItems().add(new MenuItem("C"));
        btnLinguagem.getItems().get(0).setOnAction(e -> btnLinguagem.setText("Python"));
        btnLinguagem.getItems().get(1).setOnAction(e -> btnLinguagem.setText("C++"));
        btnLinguagem.getItems().get(2).setOnAction(e -> btnLinguagem.setText("Java"));
        btnLinguagem.getItems().get(3).setOnAction(e -> btnLinguagem.setText("C"));
    }

    @FXML
    public void initialize() {
        addLanguage();
        btnSubmeter.setOnAction(e -> submeterAcao());
        initQuestion();
    }

    private void submeterAcao() {
        // recebe a entrada do usuario e transforma em arquivo
        String entrada = entradaUsuario.getText();
        String linguagem = btnLinguagem.getText();
        tranformaEmArquivo = new TranformaEmArquivo(entrada, linguagem);
        tranformaEmArquivo.solver();

        //escreve a entrada e saida referente a questao
        WriteInputOutputInFile writeInputOutputInFile = new WriteInputOutputInFile();
        writeInputOutputInFile.WriteInputsByQuestionId(questionId);
        writeInputOutputInFile.WriteOutputsByQuestionId(questionId);

        // compila e verifica a saida
        String saida = factoryJudge.getJudge(linguagem).getResult();
        // mostra o resultado
        System.out.println(saida);
        //setModalResult(saida);
    }

    public void setModalResult (String saida) {
        if (saida.equals(AC_RESULT)) {
            System.out.println("AC");
        } else if (saida.equals(WA_RESULT)) {
            System.out.println("WA");
        } else if (saida.equals(TLE_RESULT)) {
            System.out.println("TLE");
        } else if (saida.equals(RE_RESULT)) {
            System.out.println("RE");
        }
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
