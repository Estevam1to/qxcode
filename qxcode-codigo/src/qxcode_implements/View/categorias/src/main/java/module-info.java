module tela.categorias {
    requires javafx.controls;
    requires javafx.fxml;


    opens tela.categorias to javafx.fxml;
    exports tela.categorias;
}