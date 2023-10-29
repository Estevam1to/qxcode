package qxcode_implements.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import qxcode_implements.Utils.*;

public class TelaQuestion {
    TranformaEmArquivo tranformaEmArquivo;
    @FXML
    private TextField entradaUsuario;
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
        return "/qxcode_implements/View/telaQuestion.fxml";
    }

}
