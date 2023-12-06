package com.qxcode.Controller.TelasController;

import com.qxcode.Controller.ControllerQuestion;
import com.qxcode.Controller.ModalsController.*;
import com.qxcode.Controller.NavBar2Component;
import com.qxcode.Controller.NavBarComponent;
import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Main;
import com.qxcode.Model.Question;
import com.qxcode.Utils.IJudge;
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

public class TelaQuestion implements IViewController {
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

    private ControllerQuestion controllerQuestion;

    private int questionId;

    private final String AC_RESULT = "AC_RESULT";
    private final String WA_RESULT = "WA_RESULT";
    private final String TLE_RESULT = "TLE_RESULT";
    private final String RE_RESULT = "RE_RESULT";


    public TelaQuestion(){
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

    @Override
    @FXML
    public void initialize() throws IOException {
        addLanguage();
        btnSubmeter.setOnAction(e -> {
            try {
                submeterAcao();
            } catch (IOException ex) {
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

        IJudge judge = factoryJudge.getJudge(linguagem);
        boolean time = judge.getTime();
        boolean compilar = judge.compilar();
        boolean verify = judge.verifyDiff();

        if (time) {
            setModalResult(TLE_RESULT);
        }else if (!compilar) {
            setModalResult(RE_RESULT);
        }else if (!verify) {
            setModalResult(WA_RESULT);
        } else {
            setModalResult(AC_RESULT);
        }
    }

    public void setModalResult (String saida) throws IOException {
        FactoryModals factoryM = new FactoryModals();
        IControllerModal controllerModal = factoryM.getController(saida);
        Main.setModalResult(controllerModal.getPath(), controllerModal);
        factoryJudge.getJudge(btnLinguagem.getText()).destroyArquivos();
    }

    public void initQuestion(){
        Question question = controllerQuestion.getById(questionId);
        questionName.setText(question.getTitle());
        questionDescription.setText(question.getDescription());
        if (!controllerQuestion.getExTestCaseInput(question.getId()).isEmpty()) {
            questionExOutput.setText(controllerQuestion.getExTestCaseOutput(question.getId()));
            questionExInput.setText(controllerQuestion.getExTestCaseInput(question.getId()));
        } else {
            questionExOutput.setText("Não há exemplos para essa questão");
            questionExInput.setText("Não há exemplos para essa questão");
        }
    }
    public void initNavBar() throws IOException {
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
        return new FXMLLoader(resource);
    }

    private FXMLLoader obterFXMLNavBar2Loader() {
        URL resource = Main.class.getResource("View/components/navBar2.fxml");
        return new FXMLLoader(resource);
    }

    @Override
    public String getTela() {
        return "View/telaQuestion.fxml";
    }
}
