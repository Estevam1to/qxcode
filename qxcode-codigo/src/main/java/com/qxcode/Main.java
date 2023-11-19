package com.qxcode;

import com.qxcode.Controller.TelaListQuestion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/telaCategory.fxml"));

        scene = new Scene(loader.load(), 1450, 850);

        stage.setTitle("Category");

        stage.setMaxWidth(1450);
        stage.setMaxHeight(850);
        stage.setMinWidth(1450);
        stage.setMinHeight(850);//1450 e 850

        stage.setScene(scene);
        stage.show();
    }
    public static void setRoot(String tela) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(tela));

        scene.setRoot(loader.load());
    }

    public static void setRoot(String tela, String nameCategory) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(tela));
        TelaListQuestion controller = new TelaListQuestion();
        controller.setCategory(nameCategory);
        loader.setController(controller);

        scene.setRoot(loader.load());

    }
}
