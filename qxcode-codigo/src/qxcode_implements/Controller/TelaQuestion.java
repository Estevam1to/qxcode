package qxcode_implements.Controller;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class TelaQuestion {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField entradaUsuario;
    @FXML
    private Button btnSubmeter;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void inicializa() {
        location = getClass().getResource("../View/telaQuestion.fxml");
        resources = ResourceBundle.getBundle("qxcode_implements.Controller.telaQuestion");
    }

    @FXML
    public void entradaUsuario() {
        String entrada = entradaUsuario.getText();
        System.out.println(entrada);
    }

    @FXML
    public void btnSubmeter() {
        btnSubmeter.setOnAction(e -> {
            entradaUsuario();
            System.out.println("Submetido");
        });
    }

    public String getTela() {
        return "../View/telaQuestion.fxml";
    }
}