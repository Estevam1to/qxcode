package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Main;
import com.qxcode.Model.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewQuestion{

    @FXML
    private TextArea decriptionInput;
    @FXML
    private TextArea titleInput;
    @FXML
    private RadioButton difficulty1, difficulty2, difficulty3, difficulty4, difficulty5;

    @FXML
    private ChoiceBox<String> categoryInput;
    @FXML
    private ListView inputFiles;
    @FXML
    private ListView outputFiles;

    private List<File> selectedInputFiles;
    private List<File> selectedOutputFiles;
    @FXML
    private Pane navBar;

    private final QuestionDAO questionDAO;
    private final CategoryDAO categoryDAO;


    public NewQuestion() {
        this.questionDAO = new QuestionDAO();
        this.categoryDAO = new CategoryDAO();
    }

    @FXML
    public void initialize() throws IOException{
        this.initNavBar();
        loadCategoryInput();
    }

    private void loadCategoryInput(){
        List<Category> categories = categoryDAO.getAllCategories();
        for(Category category : categories){
            String title = category.getTitle();
            categoryInput.getItems().add(title);
        }
    }

    @FXML
    private void saveInputFiles(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("InputFiles", "*.in"));
        selectedInputFiles = fc.showOpenMultipleDialog(null);

        if(selectedInputFiles != null) {
            for(int i = 0; i < selectedInputFiles.size(); i++){
                inputFiles.getItems().add(selectedInputFiles.get(i).getName());
            }
        }
    }

    @FXML
    private void saveOutputFiles(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("OutputFiles", "*.sol"));
        selectedOutputFiles = fc.showOpenMultipleDialog(null);

        if(selectedOutputFiles != null) {
            for(int i = 0; i < selectedOutputFiles.size(); i++){
                outputFiles.getItems().add(selectedOutputFiles.get(i).getName());
            }
        }
    }

    private void initNavBar() throws IOException {
        FXMLLoader childLoader = obterFXMLNavBarLoader();
        AnchorPane childNode = childLoader.load();
        NavBarComponent childController = childLoader.getController();
        navBar.getChildren().add(childNode);
    }

    @FXML
    private void salvarQuestao() {
        String titulo = titleInput.getText();
        String descricao = decriptionInput.getText();
        int dificuldade = this.getDifficulty();
        int id_categoria = this.getCategory(categoryInput.getValue());

        questionDAO.insertQuestion(titulo, descricao, dificuldade, "exemplo", id_categoria);

        int idQuestion = questionDAO.getQuestionByTitle(titulo).getId();

        salvarCasosDeTeste(idQuestion);

        clearFields();
    }

    private void clearFields(){
        titleInput.clear();
        decriptionInput.clear();
        inputFiles.setItems(null);
        outputFiles.setItems(null);
        selectedInputFiles = null;
        selectedOutputFiles = null;
    }

    private void salvarCasosDeTeste(int idQuestion) {
        ControllerTestCase controllerTC = new ControllerTestCase();

        if(selectedInputFiles != null && selectedOutputFiles != null){
            controllerTC.saveTestCases(selectedInputFiles, selectedOutputFiles, idQuestion);
        }
    }

    public int getCategory(String title){
        Category category = categoryDAO.getCategoryByTitle(title);
        return category.getId();
    }

    public int getDifficulty() {
        if(difficulty1.isSelected()){
            return Integer.parseInt(difficulty1.getText());
        } else if (difficulty2.isSelected()) {
            return Integer.parseInt(difficulty2.getText());
        } else if (difficulty3.isSelected()) {
            return Integer.parseInt(difficulty3.getText());
        } else if (difficulty4.isSelected()) {
            return Integer.parseInt(difficulty4.getText());
        } else if (difficulty5.isSelected()) {
            return Integer.parseInt(difficulty5.getText());
        }
        return 0;
    }

    private FXMLLoader obterFXMLNavBarLoader() {
        URL resource = Main.class.getResource("View/components/navBar.fxml");
        if (resource == null) {
            System.out.println("FXML file not found");
        } else {
            System.out.println("FXML file found at: " + resource);
        }
        return new FXMLLoader(resource);
    }

    public String getTela() {
        return "/com/qxcode/View/telaNewQuestion.fxml";
    }

}
