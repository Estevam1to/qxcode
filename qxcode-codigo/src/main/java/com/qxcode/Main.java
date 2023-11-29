package com.qxcode;

import com.qxcode.Controller.TelaListQuestion;
import com.qxcode.Controller.TelaQuestion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class Main extends Application {

    private static Scene scene;

    public static Stack<Parent> lastScene = new Stack<Parent>();

    private static Object lastLoadedController;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("View/telaCategory.fxml"));

        scene = new Scene(loader.load());
        lastLoadedController = loader.getController();
        stage.setTitle("QXcode");

        stage.setScene(scene);
        stage.show();
    }
    public static void setRoot(String tela) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(tela));
        Object newLoadedController = loader.getController();

        if (!(lastLoadedController.equals(newLoadedController))) {
            lastScene.add(scene.getRoot());
            lastLoadedController = newLoadedController;
        }
        scene.setRoot(loader.load());
    }

    public static void backLastScreen() {
        scene.setRoot(lastScene.pop());
    }

    public static void setRoot(String tela, int categoryId, String nameCategory) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(tela));
        TelaListQuestion controller = new TelaListQuestion();
        controller.setCategory(nameCategory);
        controller.setId(categoryId);
        loader.setController(controller);

        Object newLoadedController = loader.getController();

        if (!(lastLoadedController.equals(newLoadedController))) {
            lastScene.add(scene.getRoot());
            lastLoadedController = newLoadedController;
        }
        scene.setRoot(loader.load());
    }

    public static void setRoot(String tela, int questionId) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(tela));
        TelaQuestion controller = new TelaQuestion();
        controller.setId(questionId);
        loader.setController(controller);

        Object newLoadedController = loader.getController();

        if (!(lastLoadedController.equals(newLoadedController))) {
            lastScene.add(scene.getRoot());
            lastLoadedController = newLoadedController;
        }

        scene.setRoot(loader.load());
    }

    public static <T> void setModalResult(String pathModal, T controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(pathModal));
        Parent modalParent = loader.load();
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(scene.getWindow());
        Scene modalScene = new Scene(modalParent);
        modalStage.setScene(modalScene);

        // Obtém o controlador após o carregamento do FXML
        controller = loader.getController();

        modalStage.showAndWait();
    }
}
