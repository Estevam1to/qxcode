package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.DAO.QuestionDAO;
import com.qxcode.Main;
import com.qxcode.Model.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewQuestion implements Initializable {

    @FXML
    private TextArea decriptionInput;
    @FXML
    private TextArea titleInput;
    @FXML
    private RadioButton difficulty1, difficulty2, difficulty3, difficulty4, difficulty5;
    @FXML
    private TextArea examplesInput;
    @FXML
    private ChoiceBox<String> categoryInput;
    @FXML
    private Pane navBar;

    private QuestionDAO questionDAO;
    private CategoryDAO categoryDAO;

    public NewQuestion() {
        this.questionDAO = new QuestionDAO();
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCategoryInput();
    }

    private void loadCategoryInput(){
        List<Category> categories = categoryDAO.getAllCategories();
        for(Category category : categories){
            String title = category.getTitle();
            categoryInput.getItems().add(title);
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
        String exemplos = examplesInput.getText();
        int id_categoria = this.getCategory(categoryInput.getValue());


        questionDAO.insertQuestion(titulo, descricao, dificuldade, exemplos, id_categoria);

        // Limpar os campos após a adição da categoria
        titleInput.clear();
        decriptionInput.clear();
        examplesInput.clear();


        System.out.println("Título: " + titulo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Dificuldade: " + dificuldade);
        System.out.println("Exemplos: " + exemplos);
        System.out.println("Categoria: " + id_categoria);

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
