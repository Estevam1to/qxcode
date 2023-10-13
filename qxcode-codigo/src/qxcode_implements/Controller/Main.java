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
        Parent root = FXMLLoader.load(getClass().getResource("../View/telaQuestion.fxml"));
        stage.setTitle("Question");
        stage.setScene(new Scene(root, 1450, 850));
        stage.show();
    }
}
