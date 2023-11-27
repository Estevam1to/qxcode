package com.qxcode.Controller;

import com.qxcode.Utils.TranformaEmArquivo;
import com.qxcode.Utils.FactoryJudge;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class TelaQuestion {
    TranformaEmArquivo tranformaEmArquivo;
    @FXML
    private TextArea entradaUsuario;
    @FXML
    private Button btnSubmeter;
    @FXML
    private SplitMenuButton btnLinguagem;

    private FactoryJudge factoryJudge = new FactoryJudge();

    private void addLanguage() {
        btnLinguagem.getItems().add(new MenuItem("Python"));
        btnLinguagem.getItems().add(new MenuItem("C++"));
        btnLinguagem.getItems().add(new MenuItem("Java"));
    }

    @FXML
    public void initialize() {
        addLanguage();
        btnSubmeter.setOnAction(e -> submeterAcao());
    }

    private void submeterAcao() {
        String entrada = entradaUsuario.getText();
        tranformaEmArquivo = new TranformaEmArquivo(entrada);
        tranformaEmArquivo.solver();

        String language = btnLinguagem.getText();
        factoryJudge.getJudge(language).compilar();
        factoryJudge.getJudge(language).verifyDiff();
        
        String result = factoryJudge.getJudge(language).getResult();
        if (result.equals("AC")) {
            System.out.println("AC");
        } else if (result.equals("WA")) {
            System.out.println("WA");
        } else if (result.equals("TLE")) {
            System.out.println("TLE");
        } else {
            System.out.println("ERRO");
        }

        factoryJudge.getJudge(language).destroyArquivos();
    }

    public String getTela() {
        return "com/qxcode/View/telaQuestion.fxml";
    }

}
