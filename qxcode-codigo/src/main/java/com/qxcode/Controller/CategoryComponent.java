package com.qxcode.Controller;

import com.qxcode.DAO.CategoryDAO;
import com.qxcode.Main;
import com.qxcode.Model.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class CategoryComponent {

    TelaListQuestion telaListQuestion = new TelaListQuestion();

    @FXML
    private Label categoryTitle;
    @FXML
    private Text categoryDescription;

    private CategoryDAO dao = new CategoryDAO();
    private Category categoria;



    public void setCategory(Category categoria){
        this.categoria = categoria;


        this.setarInfoCategory();
    }

    private void setarInfoCategory() {
        if (categoria != null) {
            this.categoryTitle.setText(categoria.getTitle());
            this.categoryDescription.setText(categoria.getDescription());
        } else {
            System.out.println("Erro ao setar info, category is null!");
            this.categoryTitle.setText("Deu erro!");
            this.categoryDescription.setText("Erro ao setar info, category is null!");
        }
    }



    public void entrarListQuestion(MouseEvent mouseEvent) throws IOException {
        Main.setRoot(telaListQuestion.getTela(), categoria.getId(), categoria.getTitle());
    }
}
