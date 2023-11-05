package com.qxcode.Controller;

import com.qxcode.Utils.TranformaEmArquivo;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class TelaQuestion {
    TranformaEmArquivo tranformaEmArquivo;
    @FXML
    private TextArea entradaUsuario;
    @FXML
    private Button btnSubmeter;

    @FXML
    public void initialize() {
        btnSubmeter.setOnAction(e -> submeterAcao());
    }

    private void submeterAcao() {
        String entrada = entradaUsuario.getText();
        tranformaEmArquivo = new TranformaEmArquivo(entrada);
        tranformaEmArquivo.solver();
    }

    public String getTela() {
        return "com/qxcode/View/telaQuestion.fxml";
    }

}
