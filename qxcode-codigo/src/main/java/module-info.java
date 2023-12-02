module com.qxcode {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    exports com.qxcode;
    opens com.qxcode.Controller to javafx.fxml;
    opens com.qxcode.Controller.ModalsController to javafx.fxml;
    exports com.qxcode.Controller;
    exports com.qxcode.Controller.TelasController;
    opens com.qxcode.Controller.TelasController to javafx.fxml;
    exports com.qxcode.Controller.ComponentController;
    opens com.qxcode.Controller.ComponentController to javafx.fxml;

}