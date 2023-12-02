package com.qxcode.Controller.ComponentController;

import com.qxcode.Controller.TelasController.IViewController;
import com.qxcode.Controller.TelasController.TelaListQuestion;
import com.qxcode.DAO.CategoryDAO;
import com.qxcode.Main;
import com.qxcode.Model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class CategoryComponent implements IComponentController{

    IViewController telaListQuestion = new TelaListQuestion();

    @FXML
    private Label categoryTitle;
    @FXML
    private Text categoryDescription;

    private CategoryDAO dao = new CategoryDAO();
    private Category categoria;


    public <T> void setModel(T categoria){
        this.categoria = (Category) categoria;
        this.setInfo();
    }

    public void setInfo() {
        if (categoria != null) {
            this.categoryTitle.setText(categoria.getTitle());
            this.categoryDescription.setText(categoria.getDescription());
        } else {
            System.out.println("Erro ao setar info, category is null!");
            this.categoryTitle.setText("Deu erro!");
            this.categoryDescription.setText("Erro ao setar info, category is null!");
        }
    }



    public void entrarDetalhes(MouseEvent mouseEvent) throws IOException {
        Main.setRoot(telaListQuestion.getTela(), categoria.getId(), categoria.getTitle());
    }
}
