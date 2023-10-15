package qxcode_implements.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class TelaQuestion {
    TransformaEmArquivo tranformaEmArquivo;
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
        tranformaEmArquivo = new TransformaEmArquivo(entrada);
        tranformaEmArquivo.criarArquivo();
        tranformaEmArquivo.escreverArquivo();
    }

    public String getTela() {
        return "/qxcode_implements/View/telaQuestion.fxml";
    }

}
