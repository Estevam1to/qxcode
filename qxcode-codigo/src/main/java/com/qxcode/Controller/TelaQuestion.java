package com.qxcode.Controller;

import com.qxcode.Controller.ModalsController.*;
import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Main;
import com.qxcode.Model.Question;
import com.qxcode.Utils.TranformaEmArquivo;
import com.qxcode.Utils.FactoryJudge;
import com.qxcode.Utils.WriteInputOutputInFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.MenuItem;

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
    private Text questionExOutput;
    @FXML
    private Label questionName;
    @FXML
    private Pane navBar;
    @FXML
    private Pane navBar2;

    QuestionDAO dao;

    ControllerQuestion controllerQuestion;

    private int questionId;

    private final String AC_RESULT = "AC_RESULT";
    private final String WA_RESULT = "WA_RESULT";
    private final String TLE_RESULT = "TLE_RESULT";
    private final String RE_RESULT = "RE_RESULT";


    public TelaQuestion(){
        dao = new QuestionDAO();
        controllerQuestion = new ControllerQuestion();
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
        //btnLinguagem.getItems().add(new MenuItem("Java"));
        btnLinguagem.getItems().add(new MenuItem("C"));
        btnLinguagem.getItems().get(0).setOnAction(e -> btnLinguagem.setText("Python"));
        btnLinguagem.getItems().get(1).setOnAction(e -> btnLinguagem.setText("C++"));
        //btnLinguagem.getItems().get(2).setOnAction(e -> btnLinguagem.setText("Java"));
        btnLinguagem.getItems().get(2).setOnAction(e -> btnLinguagem.setText("C"));
    }

    @FXML
    public void initialize() throws IOException {
        addLanguage();
        btnSubmeter.setOnAction(e -> {
            try {
                submeterAcao();
            } catch (IOException ex) {
                System.out.println("Erro durante a submissão da ação: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        this.initNavBar();
        initQuestion();
    }

    private void submeterAcao() throws IOException {
        // recebe a entrada do usuario e transforma em arquivo
        String entrada = entradaUsuario.getText();
        String linguagem = btnLinguagem.getText();
        tranformaEmArquivo = new TranformaEmArquivo(entrada, linguagem);
        tranformaEmArquivo.solver();

        //escreve a entrada e saida referente a questao
        WriteInputOutputInFile writeInputOutputInFile = new WriteInputOutputInFile();
        writeInputOutputInFile.WriteInputsByQuestionId(questionId);
        writeInputOutputInFile.WriteOutputsByQuestionId(questionId);

        boolean time = factoryJudge.getJudge(linguagem).getTime();
        boolean compilar = factoryJudge.getJudge(linguagem).compilar();
        boolean verify = factoryJudge.getJudge(linguagem).verifyDiff();
        if (time) {
            setModalResult(TLE_RESULT);
        }else if (!compilar) {
            setModalResult(RE_RESULT);
        }else if (!verify) {
            setModalResult(WA_RESULT);
        }
        else {
            setModalResult(AC_RESULT);
        }
    }

    public void setModalResult (String saida) throws IOException {
        IControllerModal controllerModal = null;
        FactoryModals factoryM = new FactoryModals();
        controllerModal = factoryM.getController(saida);
        Main.setModalResult(controllerModal.getPath(), controllerModal);
        factoryJudge.getJudge(btnLinguagem.getText()).destroyArquivos();
    }

    public void initQuestion(){
        Question question = dao.getQuestionById(questionId);
        questionName.setText(question.getTitle());
        questionDescription.setText(question.getDescription());
        questionExOutput.setText(controllerQuestion.getExTestCaseOutput(question.getId()));
        questionExInput.setText(controllerQuestion.getExTestCaseInput(question.getId()));
    }
    private void initNavBar() throws IOException {
        FXMLLoader childLoader = obterFXMLNavBarLoader();
        AnchorPane childNode = childLoader.load();
        NavBarComponent childController = childLoader.getController();
        navBar.getChildren().add(childNode);

        FXMLLoader childLoader2 = obterFXMLNavBar2Loader();
        AnchorPane childNode2 = childLoader2.load();
        NavBar2Component childController2 = childLoader2.getController();
        navBar2.getChildren().add(childNode2);
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


    public String getTela() {
        return "View/telaQuestion.fxml";
    }

}
