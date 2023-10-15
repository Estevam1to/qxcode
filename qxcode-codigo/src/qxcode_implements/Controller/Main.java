package qxcode_implements.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        //Carregar tela
        TelaQuestion telaQuestion = new TelaQuestion();
        Parent root = FXMLLoader.load(getClass().getResource(telaQuestion.getTela()));

        stage.setTitle("Question");

        //setar tamanho da tela
        stage.setMaxWidth(1450);
        stage.setMaxHeight(850);
        stage.setMinWidth(1450);
        stage.setMinHeight(850);

        //setar tela e mostrar tela
        stage.setScene(new Scene(root));
        stage.show();
    }
}
