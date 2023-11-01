package qxcode_implements.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;


public class TelaCategory {
    @FXML
    private GridPane gridPane;
    @FXML
    private ArrayList<Pane> categoryCards;
    
    @FXML
    public void initialize() {
        categoryCards = new ArrayList<Pane>();
        
        // Suponhamos que ControllerCategory seja uma classe com um método getTela() que retorna o caminho FXML
        ControllerCategory controllerCategory = new ControllerCategory();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(controllerCategory.getTela()));
            Pane cardCategory = loader.load();
            categoryCards.add(cardCategory);
            addCategoryCards();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCategoryCards() {
        int columnIndex = 0;
        int rowIndex = 0;
    
        for (Pane categoryCard : categoryCards) {
            GridPane.setColumnIndex(categoryCard, columnIndex);
            GridPane.setRowIndex(categoryCard, rowIndex);
    
            gridPane.getChildren().add(categoryCard);
    
            // Avança para a próxima coluna e, se necessário, para a próxima linha.
            columnIndex++;
            if (columnIndex >= gridPane.getColumnCount()) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    public String getTela() {
        return "/qxcode_implements/View/telaCategory.fxml";
    }
}
