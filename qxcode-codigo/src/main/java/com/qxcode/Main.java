package com.qxcode;

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

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/telaQuestion.fxml"));
        Parent root = loader.load();

        stage.setTitle("Category");

        stage.setMaxWidth(1450);
        stage.setMaxHeight(850);
        stage.setMinWidth(1450);
        stage.setMinHeight(850);//1450 e 850

        stage.setScene(new Scene(root));
        stage.show();
    }
}
